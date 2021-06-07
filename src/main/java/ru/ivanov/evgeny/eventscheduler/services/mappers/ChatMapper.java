package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.ChatMessage;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.AccountMinimalDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.ChatMessageDto;

@Component
public class ChatMapper {

    public ChatMessageDto mapToDto(ChatMessage chatMessage) {
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        AccountMinimalDto accountMinimalDto = new AccountMinimalDto();
        accountMinimalDto.setId(
                chatMessage.getAuthor().getId()
        );
        accountMinimalDto.setUsername(
                chatMessage.getAuthor().getUsername()
        );

        chatMessageDto.setAuthor(
                accountMinimalDto
        );
        chatMessageDto.setText(
                chatMessage.getText()
        );
        chatMessageDto.setTime(
                chatMessage.getCreateTime()
        );
        chatMessageDto.setId(
                chatMessage.getId()
        );
        return chatMessageDto;
    }
}
