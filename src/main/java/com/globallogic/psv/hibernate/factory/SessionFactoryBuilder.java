package com.globallogic.psv.hibernate.factory;

import com.globallogic.psv.hibernate.entity.Activity;
import com.globallogic.psv.hibernate.entity.Building;
import com.globallogic.psv.hibernate.entity.Report;
import com.globallogic.psv.hibernate.entity.User;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {

    public static Session getCurrentSession() {
        Session session = null;
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class)
                .addAnnotatedClass(Building.class)
                .addAnnotatedClass(Report.class)
                .addAnnotatedClass(Activity.class);
        session = configuration.buildSessionFactory().getCurrentSession();

        return session;
    }
}
