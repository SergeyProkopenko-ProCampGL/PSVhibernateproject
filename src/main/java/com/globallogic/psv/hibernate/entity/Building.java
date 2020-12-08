package com.globallogic.psv.hibernate.entity;

import com.globallogic.psv.hibernate.entity.Activity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "report_id")
    private Report report;

    @Column(name = "building_name")
    private String buildingName;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(fetch=FetchType.EAGER, mappedBy = "building")
    @Fetch(FetchMode.SELECT)
    private List<Activity> activities = new ArrayList<>();

    public Building() {
    }

    public Building(String buildingName, Boolean isActive) {
        this.buildingName = buildingName;
        this.isActive = isActive;
    }

    public Building(Report report, String buildingName, Boolean isActive, List<Activity> activities) {
        this.report = report;
        this.buildingName = buildingName;
        this.isActive = isActive;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void add(Activity activity) {
        if (activities == null) {
            activities = new ArrayList<Activity>();
        }
        activities.add(activity);
        activity.setBuilding(this);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", reportId=" + report.getId() +
                ", buildingName='" + buildingName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
