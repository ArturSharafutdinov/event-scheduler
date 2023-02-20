package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "SALE")
@AttributeOverride(name = "id", column = @Column(name = "SALE_ID"))
public class Sale extends LongIdEntity {

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @Column(name = "COUNT", nullable = false)
    private Integer count;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SERVICE_ID", nullable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name="BILL_ID", referencedColumnName = "BILL_ID")
    private Bill bill;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
