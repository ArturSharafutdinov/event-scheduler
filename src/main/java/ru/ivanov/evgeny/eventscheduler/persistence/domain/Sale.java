package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SALE")
@AttributeOverride(name = "id", column = @Column(name = "SALE_ID"))
public class Sale extends LongIdEntity {

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "COUNT", nullable = false)
    private Integer count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
