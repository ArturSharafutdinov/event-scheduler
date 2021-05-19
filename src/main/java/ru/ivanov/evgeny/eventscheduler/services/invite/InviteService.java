package ru.ivanov.evgeny.eventscheduler.services.invite;

import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;

import java.util.Set;
import java.util.UUID;

public interface InviteService {
    void registerInvite(MinimalInviteRequestDto minimalInviteRequestDto);

    void rejectInvite(UUID inviteRequestId);

    void approveInvite(UUID inviteRequestId);

    Set<InviteRequestDto> fetchAllEventInviteRequest(UUID eventId);

    Set<InviteRequest> fetchAllUserInviteRequest(Long accountId);
}
