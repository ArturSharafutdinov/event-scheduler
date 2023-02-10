package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "CINEMA")
@AttributeOverride(name = "id", column = @Column(name = "CINEMA_ID"))
public class Cinema extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
