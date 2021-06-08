package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventMember;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventMemberDto;

import java.util.UUID;

@Component
public class EventMemberMapper {

    public EventMemberDto mapToDto(EventMember eventMember) {
        EventMemberDto eventMemberDto = new EventMemberDto();

        eventMemberDto.setId((UUID) eventMember.getId());
        eventMemberDto.setAccountId(eventMember.getAccount().getId());
        eventMemberDto.setEventId((UUID) eventMember.getEvent().getId());
        eventMemberDto.setRole(eventMember.getRole());

        return eventMemberDto;
    }

}
