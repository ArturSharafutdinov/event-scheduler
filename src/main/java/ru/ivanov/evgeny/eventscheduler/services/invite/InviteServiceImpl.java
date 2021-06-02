package ru.ivanov.evgeny.eventscheduler.services.invite;

import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.InviteRequestRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.common.SimpleDao;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRequestRepository inviteRequestRepository;

    @Autowired
    private SimpleDao simpleDao;

    @Override
    @Transactional
    public void registerInvite(MinimalInviteRequestDto minimalInviteRequestDto) {
        InviteRequest inviteRequest;
        if (minimalInviteRequestDto.getId() != null) {
            Optional<InviteRequest> optional = inviteRequestRepository.findById(minimalInviteRequestDto.getId());
            if (optional.isEmpty()) {
                throw new IllegalArgumentException("invite request id is invalid");
            }
            inviteRequest = optional.get();
        } else {
            inviteRequest = new InviteRequest();
            inviteRequest.setCreateTime(
                    new Date()
            );
            Account account = simpleDao.findById(Account.class, minimalInviteRequestDto.getAccountId());
            Assert.notNull(account);

            inviteRequest.setAccount(
                    account
            );

            Event event = simpleDao.findById(Event.class, minimalInviteRequestDto.getEventId());
            Assert.notNull(event);

            inviteRequest.setEvent(
                    event
            );
            inviteRequest.setInviteStatus(
                    InviteRequest.InviteStatus.PROCESSING
            );
        }
        inviteRequest.setDescription(
                minimalInviteRequestDto.getDescription()
        );

    }

    @Override
    @Transactional
    public void rejectInvite(UUID inviteRequestId) {
        InviteRequest inviteRequest = simpleDao.findById(InviteRequest.class, inviteRequestId);
        Assert.notNull(inviteRequest);

        setInviteRequestStatusAndUpdate(inviteRequest, InviteRequest.InviteStatus.REJECTED);
    }

    @Override
    @Transactional
    public void approveInvite(UUID inviteRequestId) {
        InviteRequest inviteRequest = simpleDao.findById(InviteRequest.class, inviteRequestId);
        Assert.notNull(inviteRequest);

        setInviteRequestStatusAndUpdate(inviteRequest, InviteRequest.InviteStatus.APPROVED);
    }

    private void setInviteRequestStatusAndUpdate(InviteRequest inviteRequestStatus, InviteRequest.InviteStatus status) {
        inviteRequestStatus.setInviteStatus(
                status
        );
        simpleDao.saveOrUpdate(inviteRequestStatus);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<InviteRequestDto> fetchAllEventInviteRequest(UUID evenId) {
        Event event = simpleDao.findById(Event.class, evenId);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Set<InviteRequest> fetchAllUserInviteRequest(Long accountId) {
        return null;
    }

}
