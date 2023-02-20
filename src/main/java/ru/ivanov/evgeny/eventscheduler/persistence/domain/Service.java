package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "SERVICE")
@AttributeOverride(name = "id", column = @Column(name = "SERVICE_ID"))
public class Service extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "USERS", nullable = false)
    private Integer users;

    @ManyToOne
    @JoinColumn(name="PLATFORM_ID", nullable=false)
    private Platform platform;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
