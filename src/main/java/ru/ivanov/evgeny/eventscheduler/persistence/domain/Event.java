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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Account owner;

    //Time of occurrence of the event in the database
    @Convert(converter = EventDateConverter.class)
    @Column(name = "CREATED_TIME", nullable = false)
    private LocalDateTime createdTime;

    //Event start time (specified by users)
    @Convert(converter = EventDateConverter.class)
    @Column(name = "START_TIME", nullable = false)
    private LocalDateTime startTime;

    //The time after which the event will not be shown in the list of active
    @Convert(converter = EventDateConverter.class)
    @Column(name = "FINISH_TIME")
    private LocalDateTime finishTime;

    @Column(name = "DURATION", nullable = false)
    private Integer duration;

    //The number of participants after which the event will not be shown in the list of active
    @Column(name = "MAX_NUMBER_OF_PARTICIPANTS", nullable = false)
    private Integer maxNumberOfParticipants;

    @Column(name = "IS_PRIVATE", nullable = false)
    private Boolean isPrivate;

    //The first element is longitude, the second is latitude
    @Column(name = "LATITUDE", nullable = false)
    private Double latitude;

    //The first element is longitude, the second is latitude
    @Column(name = "LONGITUDE", nullable = false)
    private Double longitude;

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

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
