package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CITY")
@AttributeOverride(name = "id", column = @Column(name = "CITY_ID"))
public class City extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "POPULATION", nullable = false)
    private Integer population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
