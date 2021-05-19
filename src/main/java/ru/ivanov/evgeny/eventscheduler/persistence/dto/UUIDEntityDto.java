package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import java.util.UUID;

public abstract class UUIDEntityDto {

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
