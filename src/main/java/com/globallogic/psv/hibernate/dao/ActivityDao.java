package com.globallogic.psv.hibernate.dao;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.util.SessionFactoryBuilder;
import com.globallogic.psv.hibernate.service.ActivityService;
import com.globallogic.psv.hibernate.service.BuildingService;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ActivityDao {

    private Logger logger = Logger.getLogger(ActivityService.class);

    public List<Activity> getActivitiesForUserAndSpecBuilding(Long userId, Long buildingId) {

        List<Activity> activities = new ArrayList<>();
        Session session = SessionFactoryBuilder.getCurrentSession();

        try {
            session.beginTransaction();
            ReportDao reportDao = new ReportDao();
            List<Report> userReports = reportDao.getReportsByUserId(userId);
            BuildingDao buildingDao = new BuildingDao();
            List<Building> reportBuildings = buildingDao.getBuildingsByReports(userReports);
            logger.info("Building list: " + reportBuildings);
            BuildingService buildingService = new BuildingService();
            List<Long> buildingIds = buildingService.getBuildingIds(reportBuildings);
            logger.info("Building ids list for user " + userId + ": " + buildingIds);

            if (buildingIds.contains(buildingId)) {
                activities = session.createQuery("from Activity where building.id =:buildingId").
                        setParameter("buildingId", buildingId).getResultList();
            }
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }
        return activities;
    }
}
