package ru.ivanov.evgeny.eventscheduler.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.InviteRequest;

import java.util.UUID;

public interface InviteRequestRepository extends JpaRepository<InviteRequest, UUID> {
}
