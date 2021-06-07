package ru.ivanov.evgeny.eventscheduler.services.chat;

import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.ChatRoom;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.ChatMessageDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EditableMessageDto;

import java.util.List;

public interface ChatService {
    /**
     * Проверят, может ли Account открыть/писать чат
     */
    Boolean chatIsAvailable(Account account, ChatRoom.ChatRoomType type, String key);

    ChatMessageDto sendMessage(Account account, ChatRoom.ChatRoomType type, String key, EditableMessageDto message);

    List<ChatMessageDto> loadMessages(Account account, ChatRoom.ChatRoomType type, String id);

}
