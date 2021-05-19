package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import java.util.UUID;

public class MinimalInviteRequestDto extends UUIDEntityDto {
    private UUID eventId;
    private Long accountId;
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
