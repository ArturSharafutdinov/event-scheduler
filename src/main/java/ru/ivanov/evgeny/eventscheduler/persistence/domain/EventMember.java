package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.UUIDEntity;
import ru.ivanov.evgeny.eventscheduler.persistence.enums.EventRole;

import javax.persistence.*;

@Entity
@Table(name = "EVENT_MEMBER")
@AttributeOverride(name = "id", column = @Column(name = "EVENT_MEMBER_ID"))
public class EventMember extends UUIDEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private EventRole role;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public EventRole getRole() {
        return role;
    }

    public void setRole(EventRole role) {
        this.role = role;
    }
}
