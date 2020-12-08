package com.globallogic.psv.hibernate.service;

import com.globallogic.psv.hibernate.dao.ActivityDao;
import com.globallogic.psv.hibernate.dao.BuildingDao;
import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.factory.SessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class BuildingService {

    private Logger logger = Logger.getLogger(ActivityService.class);

    public List<Activity> findAllActivitiesByBuildingId(Long buildingId) {
        BuildingDao buildingDao = new BuildingDao();
        List<Activity> allActivities = buildingDao.findAllActivitiesByBuildingId(buildingId);
        logger.info("All activities for building id = " + buildingId + ": " + allActivities);
        return allActivities;
    }

    public void updateIsActiveStatus(Long buildingId, double specificValue) {
        BuildingDao buildingDao = new BuildingDao();
        buildingDao.updateBuildingStatus(buildingId, specificValue);
    }

    public List<Long> getBuildingIds(List<Building> reportBuildings) {
        List<Long> buildingIds = new ArrayList<>();
        reportBuildings.forEach(elem -> buildingIds.add(elem.getId()));
        return buildingIds;
    }
}

