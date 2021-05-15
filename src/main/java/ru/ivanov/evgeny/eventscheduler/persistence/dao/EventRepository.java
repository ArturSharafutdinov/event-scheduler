package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Event;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
