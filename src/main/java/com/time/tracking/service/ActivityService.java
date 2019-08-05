package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.entityConverter.ActivityConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.ActivityDao;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;

@InitializeComponent
public class ActivityService {

    private final ActivityDao activityDao;
    private final ActivityConverter activityConverter;

    public ActivityService(ActivityDao activityDao, ActivityConverter activityConverter) {
        this.activityDao = activityDao;
        this.activityConverter = activityConverter;
    }

    public void createActivity(ActivityDto activityDto) throws ServiceException {
        try {
            Activity activity = activityConverter.convert(activityDto);
            activityDao.insert(activity);
        } catch (Exception e) {
            throw new ServiceException("Activity create failed", e);
        }
    }

}
