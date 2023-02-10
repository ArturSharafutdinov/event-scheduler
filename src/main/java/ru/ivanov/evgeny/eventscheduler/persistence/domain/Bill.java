package ru.ivanov.evgeny.eventscheduler.persistence.domain;

import ru.ivanov.evgeny.eventscheduler.persistence.common.identity.LongIdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BILL")
@AttributeOverride(name = "id", column = @Column(name = "BILL_ID"))
public class Bill extends LongIdEntity {

    @Column(name = "BILL_DATE", nullable = false)
    private Date billDate;

    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "BILL_SALES",
            joinColumns = @JoinColumn(name = "BILL_ID"),
            inverseJoinColumns =  @JoinColumn(name = "SALE_ID")
    )
    private List<Sale> sales = new ArrayList<>();

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date checkDate) {
        this.billDate = checkDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
