package ru.ivanov.evgeny.eventscheduler.services.chat;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.ChatMessageRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.ChatRoomRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.EventMemberRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.common.SimpleDao;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.*;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.ChatMessageDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EditableMessageDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.NotificationDto;
import ru.ivanov.evgeny.eventscheduler.services.mappers.ChatMapper;
import ru.ivanov.evgeny.eventscheduler.websocket.service.WebsocketService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    private EventMemberRepository eventMemberRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Autowired
    private WebsocketService websocketService;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    private static final int maxMessageLength = 2000;

    @Override
    @Transactional(readOnly = true)
    public Boolean chatIsAvailable(Account account, ChatRoom.ChatRoomType genre, String key) {

        if (ChatRoom.ChatRoomType.EVENT.equals(genre)) {
            Event event = simpleDao.findById(Event.class, UUID.fromString(key));
            if (Objects.nonNull(event)) {
                // является участником мероприятия и чат доступен
                EventMember eventMember = eventMemberRepository.findByAccountAndEvent(account, event);
                return Objects.nonNull(eventMember);
            }
            return false;
        }

        if (ChatRoom.ChatRoomType.PRIVATE.equals(genre)) {
            throw new IllegalArgumentException("PRIVATE CHAT IS NOT IMPLEMENTED");
            //TODO
        }
        return false;
    }

    @Override
    @Transactional
    public ChatMessageDto sendMessage(Account account, ChatRoom.ChatRoomType genre, String key, EditableMessageDto message) {
        Assert.state(message.getText().length() < maxMessageLength, "Message too long");
        Boolean isAvailable = chatIsAvailable(account, genre, key);

        if (!isAvailable) throw new IllegalArgumentException("Chat is not available");

        ChatRoom chatRoom = getChatRoom(genre, key);

        ChatMessage chatMessage = createMessage(account, chatRoom, message.getText());

        return sendMessage(account, chatRoom, chatMessage);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatMessageDto> loadMessages(Account account, ChatRoom.ChatRoomType genre, String key) {
        ChatRoom chatRoom = findRoom(genre, key);
        if (chatRoom != null) {
            List<ChatMessage> chatMessages = chatMessageRepository.findAllByRoomOrderByCreateTime(chatRoom);
            if (CollectionUtils.isNotEmpty(chatMessages)) {
                return chatMessages.stream()
                        .map(item -> chatMapper.mapToDto(item))
                        .collect(Collectors.toList());
            }

        }
        return new ArrayList<>();
    }

    private ChatRoom getChatRoom(ChatRoom.ChatRoomType genre, String key) {
        ChatRoom room = findRoom(genre, key);
        if (room != null) {
            return room;
        }
        return createRoom(genre, key);
    }

    private ChatRoom findRoom(ChatRoom.ChatRoomType genre, String key) {
        return chatRoomRepository.findByTypeAndKey(genre, key);
    }

    private ChatMessage createMessage(Account account, ChatRoom chatRoom, String text) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setAuthor(account);
        chatMessage.setRoom(chatRoom);
        chatMessage.setCreateTime(new Date());
        chatMessage.setText(text);
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    private ChatRoom createRoom(ChatRoom.ChatRoomType genre, String key) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setType(genre);
        chatRoom.setKey(key);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    private ChatMessageDto sendMessage(Account account, ChatRoom chatRoom, ChatMessage chatMessage) {
        String resolvedPath = resolveWebSocketPath(chatRoom);
        ChatMessageDto chatMessageDto = chatMapper.mapToDto(chatMessage);

        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType("NEW_MESSAGE");
        notificationDto.setPayload(chatMessageDto);

        websocketService.send(resolvedPath, notificationDto);
        return chatMessageDto;
    }

    private String resolveWebSocketPath(ChatRoom chatRoom) {
        if (ChatRoom.ChatRoomType.EVENT.equals(chatRoom.getType())) {
            return "/chat/event/".concat(chatRoom.getKey());
        }
        if (ChatRoom.ChatRoomType.PRIVATE.equals(chatRoom.getType())) {
            //TODO
        }
        throw new IllegalArgumentException("PRIVATE CHAT IN NOT IMPLEMENTED");
    }

}
