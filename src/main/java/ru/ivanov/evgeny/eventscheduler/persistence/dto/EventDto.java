package ru.ivanov.evgeny.eventscheduler.persistence.dto;

import java.time.LocalDateTime;

public class EventDto {

    private String name;

    private String description;

    private Long owner_id;

    private LocalDateTime startTime;

    private LocalDateTime finishTime;

    private Integer maxNumberOfParticipants;

    private Boolean isPrivate;

    private Double[] coordinates;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getMaxNumberOfParticipants() {
        return maxNumberOfParticipants;
    }

    public void setMaxNumberOfParticipants(Integer maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
}
