package com.globallogic.psv.hibernate.entity;

import com.globallogic.psv.hibernate.entity.Building;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="report")
public class Report {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="report_name")
    private String reportName;

    @Column(name="price")
    private Double price;

    @Column(name="report_date")
    @Temporal(TemporalType.DATE)
    private Date reportDate;

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "report", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Building> buildings;

    public Report (){
    }

    public Report(String reportName, Double price, Date reportDate) {
        this.reportName = reportName;
        this.price = price;
        this.reportDate = reportDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public void add(Building building) {
        if (buildings == null) {
            buildings = new ArrayList<Building>();
        }
        buildings.add(building);
        building.setReport(this);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", reportName='" + reportName + '\'' +
                ", price=" + price +
                ", reportDate=" + reportDate +
                '}';
    }
}
