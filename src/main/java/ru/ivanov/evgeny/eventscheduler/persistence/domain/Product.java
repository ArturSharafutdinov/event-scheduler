package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@AttributeOverride(name = "id", column = @Column(name = "PRODUCT_ID"))
public class Product extends LongIdEntity {

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private Type type;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
