package com.time.tracking.service;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.dtoConverter.ActivityDtoFromEntityConverter;
import com.time.tracking.converter.entityConverter.ActivityConverter;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dao.ActivityDao;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;

import java.util.List;
import java.util.stream.Collectors;

@InitializeComponent
public class ActivityService {

    private final ActivityDao activityDao;
    private final ActivityConverter activityConverter;
    private final ActivityDtoFromEntityConverter activityDtoFromEntityConverter;

    public ActivityService(ActivityDao activityDao,
                           ActivityConverter activityConverter,
                           ActivityDtoFromEntityConverter activityDtoFromEntityConverter) {
        this.activityDao = activityDao;
        this.activityConverter = activityConverter;
        this.activityDtoFromEntityConverter = activityDtoFromEntityConverter;
    }

    public void createActivity(ActivityDto activityDto) throws ServiceException {
        try {
            Activity activity = activityConverter.convert(activityDto);
            activityDao.insert(activity);
        } catch (Exception e) {
            throw new ServiceException("Activity create failed", e);
        }
    }

    public List<ActivityDto> receiveFreeActivitiesForUser(int userId) throws ServiceException {
        try {
            List<Activity> activities = activityDao.receiveFreeActivitiesForUser(userId);
            return activities.stream().map(activityDtoFromEntityConverter::convert).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ServiceException("Activity receive failed", e);
        }
    }
}
