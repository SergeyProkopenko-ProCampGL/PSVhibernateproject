package com.globallogic.psv.hibernate.dao;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.util.SessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ReportDao {

    private static final Logger logger = Logger.getLogger(ReportDao.class);

    public List<Report> getReportsByUserId(Long userId) {
        List<Report> userReports = new ArrayList<>();
        Session session = SessionFactoryBuilder.getCurrentSession();
        try {
            session.beginTransaction();
            userReports = session.createQuery("from Report where user.id =:userId").
                    setParameter("userId", userId).getResultList();
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }
        return userReports;
    }

    public List<Activity> findAllActivitiesByReportId(Long reportId) {
        Session session = SessionFactoryBuilder.getCurrentSession();
        List<Activity> allActivities = new ArrayList<>();
        try {
            session.beginTransaction();
            Report report = session.get(Report.class, reportId);
            List<Building> buildings = report.getBuildings();
            buildings.stream().forEach(elem -> allActivities.addAll(elem.getActivities()));
            session.getTransaction().commit();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        } finally {
            session.close();
        }
        return allActivities;
    }

}
