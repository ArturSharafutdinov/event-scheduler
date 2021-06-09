package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.services.auth.AccountService;

import java.util.UUID;

@Component
public class InviteRequestMapper {

    public InviteRequestDto mapToDto(InviteRequest inviteRequest){
        InviteRequestDto inviteRequestDto = new InviteRequestDto();

        inviteRequestDto.setId((UUID) inviteRequest.getId());
        inviteRequestDto.setAccountId(inviteRequest.getAccount().getId());
        inviteRequestDto.setEventId((UUID) inviteRequest.getEvent().getId());
        inviteRequestDto.setDescription(inviteRequest.getDescription());
        inviteRequestDto.setUsername(inviteRequest.getAccount().getUsername());

        return inviteRequestDto;

    }

}
