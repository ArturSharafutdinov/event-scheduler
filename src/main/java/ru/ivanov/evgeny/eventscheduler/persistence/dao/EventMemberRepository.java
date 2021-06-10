package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventMember;

import java.util.List;
import java.util.UUID;

public interface EventMemberRepository extends JpaRepository<EventMember, UUID> {
    EventMember findByAccountAndEvent(Account account, Event event);

    List<EventMember> findAllByEvent(Event event);

    List<EventMember> findAllByAccount(Account account);
}
