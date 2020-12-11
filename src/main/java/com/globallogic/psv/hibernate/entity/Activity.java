package com.globallogic.psv.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="building_id")
    private Building building;

    @Column(name = "work_name")
    private String workName;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount")
    private Double amount;

    public Activity() {
    }

    public Activity(String workName, Double price, Double amount) {
        this.workName = workName;
        this.price = price;
        this.amount = amount;
    }

    public Activity(Building building, String workName, Double price, Double amount) {
        this.building = building;
        this.workName = workName;
        this.price = price;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", buildingId=" + building.getId() +
                ", workName='" + workName + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
