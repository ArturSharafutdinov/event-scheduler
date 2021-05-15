package ru.ivanov.evgeny.eventscheduler.persistence.dto;

public abstract class LongIdEntityDto {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}