package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.converters.EventDateConverter;
import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.InviteStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "INVITE_REQUEST")
@AttributeOverride(name = "uuid", column = @Column(name = "INVITE_REQUEST_ID"))
public class InviteRequest extends UUIDEntity {

    @ManyToOne
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Convert(converter = EventDateConverter.class)
    @Column(name = "CREATE_TIME")
    private LocalDateTime createTime;

    @Convert(converter = EventDateConverter.class)
    @Column(name = "FINISH_TIME")
    private LocalDateTime finishTime;

    // Почему должен попасть именно этот account и т.д
    @Column(name = "DESCRIPTION")
    private String description;

    // TODO status invita чтобы инфу человеку дать.И шедулер, который будет прыгать и по дате смотреть.
    @Enumerated(EnumType.STRING)
    @Column(name = "INVITE_STATUS")
    private InviteStatus inviteStatus;

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event getEvent() {
        return event;
    }

    public InviteStatus getInviteStatus() {
        return inviteStatus;
    }

    public void setInviteStatus(InviteStatus inviteStatus) {
        this.inviteStatus = inviteStatus;
    }
}
