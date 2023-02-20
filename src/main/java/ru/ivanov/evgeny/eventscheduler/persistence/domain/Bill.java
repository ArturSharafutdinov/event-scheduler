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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill", cascade = {CascadeType.ALL})
    private List<Sale> sales = new ArrayList<>();

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date checkDate) {
        this.billDate = checkDate;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }
}
