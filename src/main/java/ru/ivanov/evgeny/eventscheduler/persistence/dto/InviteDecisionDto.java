package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import java.util.UUID;

public class InviteDecisionDto {

    private UUID eventId;

    private Long accountId;

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
}
