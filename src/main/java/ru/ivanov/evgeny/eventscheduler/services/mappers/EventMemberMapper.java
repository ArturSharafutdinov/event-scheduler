package ru.ivanov.evgeny.eventscheduler.services.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventMember;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.EventMemberDto;

import java.util.UUID;

@Component
public class EventMemberMapper {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private AccountMapper accountMapper;

    public EventMemberDto mapToDto(EventMember eventMember) {
        EventMemberDto eventMemberDto = new EventMemberDto();

        eventMemberDto.setId((UUID) eventMember.getId());
        eventMemberDto.setAccountId(eventMember.getAccount().getId());
        eventMemberDto.setEventId((UUID) eventMember.getEvent().getId());
        eventMemberDto.setRole(eventMember.getRole());
        eventMemberDto.setAccount(accountMapper.mapToDto(eventMember.getAccount()));

        return eventMemberDto;
    }

    public EventMemberDto mapToDto(EventMember eventMember, Account account) {
        EventMemberDto eventMemberDto = mapToDto(eventMember);
        if (account.getImageInfo() != null) {
            eventMemberDto.setFileInfoDto(fileInfoMapper.mapToDto(account.getImageInfo()));
        }
        return eventMemberDto;
    }

}
