package com.globallogic.psv.hibernate.service;

import com.globallogic.psv.hibernate.dao.ReportDao;
import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Report;
import org.apache.log4j.Logger;

import java.util.List;

public class ReportService {

    private static final Logger logger = Logger.getLogger(ReportService.class);
    ReportDao reportDao = new ReportDao();

    public List<Report> getReportsByParticularUser(Long userId) {
        List<Report> reports = reportDao.getReportsByUserId(userId);
        logger.info("All reports for user id = " + userId + ": " + reports);

        return reports;
    }

    public List<Activity> findAllActivitiesByReportId(Long reportId) {
        List<Activity> allActivities = reportDao.findAllActivitiesByReportId(reportId);
        logger.info("All activities for specific report id = " + reportId + ": " + "\n" + allActivities);

        return allActivities;
    }
}
