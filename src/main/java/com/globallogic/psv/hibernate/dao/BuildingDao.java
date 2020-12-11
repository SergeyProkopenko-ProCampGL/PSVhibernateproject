package com.globallogic.psv.hibernate.dao;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.util.SessionFactoryBuilder;
import com.globallogic.psv.hibernate.service.ActivityService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Transaction tx = session.beginTransaction();
        try {
            if (totalPrice > specificValue) {
                String sqlUpdateFalse = "UPDATE building SET is_active = 0 WHERE id = ".
                        concat("'").concat(buildingId.toString()).concat("'");
                session.createNativeQuery(sqlUpdateFalse).executeUpdate();
                logger.info("Set building id=" + buildingId + " status is active = FALSE");
            } else {
                String sqlUpdateTrue = "UPDATE building SET is_active = 1 WHERE id = ".
                        concat("'").concat(buildingId.toString()).concat("'");
                session.createNativeQuery(sqlUpdateTrue).executeUpdate();
                logger.info("Set building id=" + buildingId + " status is active = TRUE");
            }
            tx.commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            tx.rollback();
        } finally {
            session.close();
        }
    }

}

