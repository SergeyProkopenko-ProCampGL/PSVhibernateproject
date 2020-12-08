package com.globallogic.psv.hibernate.service;

import com.globallogic.psv.hibernate.dao.ActivityDao;
import com.globallogic.psv.hibernate.entity.Activity;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class ActivityService {

    private Logger logger = Logger.getLogger(ActivityService.class);

    public List<Activity> getActivitiesForUserAndSpecBuilding(Long userId, Long buildingId) {

        ActivityDao activityDao = new ActivityDao();
        List<Activity> activities = activityDao.getActivitiesForUserAndSpecBuilding(userId, buildingId);
        if (!activities.isEmpty()) {
            logger.info("Getting activities by user id=" + userId + " and specific building id=" + buildingId + ": " +
                    "\n" + activities);
        } else {
            logger.info("No activities for user id=" + userId + " and specific building id=" + buildingId);
        }
        return activities;
    }

    public double getTotalActivityPriceByReportId(Long reportId) {
        ReportService reportService = new ReportService();
        List<Activity> allActivitiesByReportId = reportService.findAllActivitiesByReportId(reportId);
        logger.info("All activities by report id=" + reportId + ": " + allActivitiesByReportId);
        List<Double> activityPrices = getTotalPrice(allActivitiesByReportId);

        return activityPrices.stream().mapToDouble(x -> x).sum();

    }

    public double getTotalActivityPriceByBuildingId(Long buildingId) {
        BuildingService buildingService = new BuildingService();
        List<Activity> allActivitiesByBuildingId = buildingService.findAllActivitiesByBuildingId(buildingId);
        logger.info("All activities by building id=" + buildingId + ": " + allActivitiesByBuildingId);
        List<Double> activityPrices = getTotalPrice(allActivitiesByBuildingId);

        return activityPrices.stream().mapToDouble(x->x).sum();
    }

    public double getTotalActivityPriceByUserId(Long userId) {
        UserService userService = new UserService();
        List<Activity> allActivitiesByUserId = userService.findAllActivitiesByUserId(userId);
        logger.info("All activities by user " + userId + ": " + allActivitiesByUserId);
        List<Double> activityPrices = getTotalPrice(allActivitiesByUserId);

        return activityPrices.stream().mapToDouble(x->x).sum();
    }

    private List<Double> getTotalPrice(List<Activity> allActivityByBuildingId) {
        List<Double> activityPrices = new ArrayList<>();
        allActivityByBuildingId.forEach(elem -> activityPrices.add(elem.getPrice() * elem.getAmount()));
        return activityPrices;
    }
}

