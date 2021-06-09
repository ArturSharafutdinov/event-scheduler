package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;

import java.util.Set;
import java.util.UUID;

public interface InviteRequestRepository extends JpaRepository<InviteRequest, UUID> {

    InviteRequest findByAccountAndEvent(Account account, Event event);

    Set<InviteRequest> findAllByEvent(Event event);

}
