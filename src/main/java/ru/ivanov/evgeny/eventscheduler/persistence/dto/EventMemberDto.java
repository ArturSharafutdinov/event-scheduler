package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import ru.ivanov.evgeny.eventscheduler.persistence.enums.EventRole;

import java.util.UUID;

public class EventMemberDto {

    private UUID id;

    private UUID eventId;

    private Long accountId;

    private EventRole role;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public EventRole getRole() {
        return role;
    }

    public void setRole(EventRole role) {
        this.role = role;
    }
}
