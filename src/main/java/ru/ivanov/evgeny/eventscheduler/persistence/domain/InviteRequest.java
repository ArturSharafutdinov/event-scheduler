package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;

import javax.persistence.*;
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

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "FINISH_TIME")
    private Date finishTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
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

    public enum InviteStatus {
        APPROVED,
        REJECTED,
        PROCESSING
    }
}
