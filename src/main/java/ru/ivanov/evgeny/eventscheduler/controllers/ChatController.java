package ru.ivanov.evgeny.eventscheduler.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.ChatRoom;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.ChatMessageDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EditableMessageDto;
import ru.ivanov.evgeny.eventscheduler.services.chat.ChatService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/chat/is-available")
    public Boolean chatIsAvailable(Account account,
                                   @RequestParam(name = "type") ChatRoom.ChatRoomType type,
                                   @RequestParam(name = "key") String key) {
        return chatService.chatIsAvailable(account, type, key);
    }

    @PostMapping("/chat/{type}/{id}/send")
    public ChatMessageDto sendMessage(Account account,
                                      @PathVariable(name = "type") ChatRoom.ChatRoomType type,
                                      @PathVariable(name = "id") String id,
                                      @RequestBody EditableMessageDto message) {
        return chatService.sendMessage(account, type, id, message);
    }

    @GetMapping("/chat/{type}/{id}")
    public List<ChatMessageDto> loadMessages(Account account,
                                             @PathVariable(name = "type") ChatRoom.ChatRoomType type,
                                             @PathVariable(name = "id") String id) {
        return chatService.loadMessages(account, type, id);
    }
}
