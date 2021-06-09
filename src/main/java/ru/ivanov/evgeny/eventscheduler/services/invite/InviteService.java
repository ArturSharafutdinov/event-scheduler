package ru.ivanov.evgeny.eventscheduler.services.invite;

import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.InviteStatus;

import java.util.Set;
import java.util.UUID;

public interface InviteService {
    void registerInvite(MinimalInviteRequestDto minimalInviteRequestDto);

    void rejectInvite(Account account, UUID inviteRequestId);

    void approveInvite(Account account, UUID inviteRequestId);

    Set<InviteRequestDto> fetchAllEventInviteRequest(Account account, UUID eventId);

    Set<InviteRequest> fetchAllUserInviteRequest(Long accountId);

    InviteStatus getInviteStatus(Account account, UUID eventId);

}
