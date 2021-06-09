package ru.ivanov.evgeny.eventscheduler.services.invite;

import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.EventMemberRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.EventRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.InviteRequestRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.dao.common.SimpleDao;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventMember;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.InviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.dto.MinimalInviteRequestDto;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.EventRole;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.InviteStatus;
import ru.ivanov.evgeny.eventscheduler.services.mappers.InviteRequestMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRequestRepository inviteRequestRepository;

    @Autowired
    private SimpleDao simpleDao;

    @Autowired
    private InviteRequestMapper inviteRequestMapper;

    @Autowired
    private EventMemberRepository eventMemberRepository;

    private EventRepository eventRepository;

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
                    LocalDateTime.now()
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
                    InviteStatus.PROCESSING
            );
            inviteRequest.setDescription(
                    minimalInviteRequestDto.getDescription()
            );
            inviteRequestRepository.save(inviteRequest);
        }

    }

    @Override
    @Transactional
    public void rejectInvite(Account account, UUID inviteRequestId) {
        InviteRequest inviteRequest = simpleDao.findById(InviteRequest.class, inviteRequestId);
        Assert.notNull(inviteRequest);
        Event event = inviteRequest.getEvent();
        if (!event.getOwner().equals(account)) {
            throw new IllegalArgumentException("User " + account.getUsername() + " has no access to reject invite");
        }
        inviteRequest.setFinishTime(LocalDateTime.now());
        setInviteRequestStatusAndUpdate(inviteRequest, InviteStatus.REJECTED);
    }

    @Override
    @Transactional
    public void approveInvite(Account account, UUID inviteRequestId) {
        InviteRequest inviteRequest = simpleDao.findById(InviteRequest.class, inviteRequestId);
        Assert.notNull(inviteRequest);
        Event event = inviteRequest.getEvent();
        if (!event.getOwner().equals(account)) {
            throw new IllegalArgumentException("User " + account.getUsername() + " has no access to approve invite");
        }
        inviteRequest.setFinishTime(LocalDateTime.now());
        setInviteRequestStatusAndUpdate(inviteRequest, InviteStatus.APPROVED);
        setUserAsEventMember(inviteRequest.getAccount(),inviteRequest.getEvent());
    }

    private void setInviteRequestStatusAndUpdate(InviteRequest inviteRequestStatus, InviteStatus status) {
        inviteRequestStatus.setInviteStatus(
                status
        );
        inviteRequestRepository.save(inviteRequestStatus);
    }

    private void setUserAsEventMember(Account account, Event event){
        EventMember eventMember = new EventMember();
        eventMember.setAccount(account);
        eventMember.setEvent(event);
        eventMember.setRole(EventRole.USER);
        eventMemberRepository.save(eventMember);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<InviteRequestDto> fetchAllEventInviteRequest(Account account, UUID eventId) {
        Event event = simpleDao.findById(Event.class, eventId);
        if (!event.getOwner().equals(account)) {
            throw new IllegalArgumentException("User " + account.getUsername() + " has no access to get invites list");
        }
        return inviteRequestRepository.findAllByEvent(event)
                .stream()
                .filter(inviteRequest -> inviteRequest.getFinishTime()==null)
                .map(inviteRequest -> inviteRequestMapper.mapToDto(inviteRequest))
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<InviteRequest> fetchAllUserInviteRequest(Long accountId) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public InviteStatus getInviteStatus(Account account, UUID eventId) {
        Event event = simpleDao.findById(Event.class, eventId);
        InviteRequest request = inviteRequestRepository.findByAccountAndEvent(account, event);
        if (request == null) {
            return InviteStatus.NO_STATUS;
        }
        return request.getInviteStatus();
    }


}
