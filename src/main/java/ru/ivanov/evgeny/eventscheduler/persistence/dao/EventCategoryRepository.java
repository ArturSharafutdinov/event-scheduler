package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.EventCategory;

import java.util.UUID;

public interface EventCategoryRepository extends JpaRepository<EventCategory, UUID> {
}
