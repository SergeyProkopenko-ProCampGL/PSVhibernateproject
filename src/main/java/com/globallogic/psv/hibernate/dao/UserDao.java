package com.globallogic.psv.hibernate.dao;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.util.SessionFactoryBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public List<Activity> findAllActivitiesByUserId(Long userId) {
        List<Activity> activities = new ArrayList<>();

        Session session = SessionFactoryBuilder.getCurrentSession();

        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Report.class);
            List<Report> reports = (List<Report>) criteria.add(Restrictions.eq("user.id", userId)).list();
            List<Building> buildings = new ArrayList<>();
            reports.stream().forEach(elem -> buildings.addAll(elem.getBuildings()));
            buildings.stream().forEach(elem -> activities.addAll(elem.getActivities()));

            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return activities;
    }
}
