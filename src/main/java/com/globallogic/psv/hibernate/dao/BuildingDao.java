package com.globallogic.psv.hibernate.dao;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.factory.SessionFactoryBuilder;
import com.globallogic.psv.hibernate.service.ActivityService;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BuildingDao {

    private Logger logger = Logger.getLogger(BuildingDao.class);

    public List<Building> getBuildingsByReports(List<Report> userReports) {
        List<Building> buildings = new ArrayList<>();
        Session session = SessionFactoryBuilder.getCurrentSession();
        try {
            session.beginTransaction();
            buildings = session.createQuery("from Building where report in (:userReports)").
                    setParameter("userReports", userReports).getResultList();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }
        return buildings;
    }

    public List<Activity> findAllActivitiesByBuildingId(Long buildingId) {
        List<Activity> allActivities = new ArrayList<>();
        Session session = SessionFactoryBuilder.getCurrentSession();
        try {
            session.beginTransaction();
            Building building = session.get(Building.class, buildingId);
            allActivities = building.getActivities();
            session.getTransaction().commit();

        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }

        return allActivities;
    }

    public void updateBuildingStatus(Long buildingId, double specificValue) {
        ActivityService activityService = new ActivityService();
        double totalPrice = activityService.getTotalActivityPriceByBuildingId(buildingId);
        Building building = null;

        Session session = SessionFactoryBuilder.getCurrentSession();
        try {
            if (totalPrice > specificValue) {
                session.beginTransaction();
                building = session.get(Building.class, buildingId);
                building.setIsActive(false);
                logger.info("Set building id=" + buildingId + " status is active = FALSE");
            } else {
                building.setIsActive(true);
                logger.info("Set building id=" + buildingId + " status is active = TRUE");
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }
    }

}

