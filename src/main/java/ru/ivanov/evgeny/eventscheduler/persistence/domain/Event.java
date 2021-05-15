package ru.ivanov.evgeny.eventscheduler.persistence.domain;


import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;
import ru.ivanov.evgeny.eventscheduler.persistence.domain.Account;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "EVENT")
@AttributeOverride(name = "uuid", column = @Column(name = "EVENT_ID"))
public class Event extends UUIDEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account owner;

    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    @Column(name = "FINISH_TIME")
    private LocalDateTime finishTime;

    @Column(name = "COMPLETED_TIME")
    private LocalDateTime completedTime;

    @Column(name = "MAX_NUMBER_OF_PARTICIPANTS")
    private Integer maxNumberOfParticipants;

    @Column(name = "IS_PRIVATE")
    private Boolean isPrivate;

    @Column(name = "COORDINATES")
    private String coordinates;

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

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
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

    public LocalDateTime getCompletedTime() {
        return completedTime;
    }

    public void setCompletedTime(LocalDateTime completedTime) {
        this.completedTime = completedTime;
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

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
