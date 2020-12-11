package com.globallogic.psv.hibernate.service;

import com.globallogic.psv.hibernate.dao.UserDao;
import com.globallogic.psv.hibernate.entity.Activity;
import org.apache.log4j.Logger;

import java.util.List;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    public List<Activity> findAllActivitiesByUserId(Long userId) {
        UserDao userDao = new UserDao();
        List<Activity> activities = userDao.findAllActivitiesByUserId(userId);
        logger.info("All activities for user id = " + userId + ": " + activities);
        return userDao.findAllActivitiesByUserId(userId);
    }
}
