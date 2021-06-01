package ru.ivanov.evgeny.eventscheduler.persistence.domain;


import ru.ivanov.evgeny.eventscheduler.persistence.common.converters.EventDateConverter;
import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Account owner;

    //Time of occurrence of the event in the database
    @Convert(converter = EventDateConverter.class)
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    //Event start time (specified by users)
    @Convert(converter = EventDateConverter.class)
    @Column(name = "START_TIME")
    private LocalDateTime startTime;

    //The time after which the event will not be shown in the list of active
    @Convert(converter = EventDateConverter.class)
    @Column(name = "FINISH_TIME")
    private LocalDateTime finishTime;

    @Column(name = "DURATION")
    private Integer duration;

    //The number of participants after which the event will not be shown in the list of active
    @Column(name = "MAX_NUMBER_OF_PARTICIPANTS")
    private Integer maxNumberOfParticipants;

    @Column(name = "IS_PRIVATE")
    private Boolean isPrivate;

    //The first element is longitude, the second is latitude
    @Column(name = "COORDINATES")
    private String coordinates;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private Set<InviteRequest> inviteRequest = new HashSet<>();

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public void setPrivate(Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public Set<InviteRequest> getInviteRequest() {
        return inviteRequest;
    }

    public void setInviteRequest(Set<InviteRequest> inviteRequest) {
        this.inviteRequest = inviteRequest;
    }
}
