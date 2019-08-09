package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.model.entity.ActivitySession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ActivitySessionConverter implements Converter<ActivitySessionDto, ActivitySession> {

    private static final Logger LOGGER = LogManager.getLogger(ActivitySessionConverter.class);
    private final ActivityConverter activityConverter;
    private final UserConverter userConverter;

    public ActivitySessionConverter(ActivityConverter activityConverter, UserConverter userConverter) {
        this.activityConverter = activityConverter;
        this.userConverter = userConverter;
    }

    @Override
    public ActivitySession convert(ActivitySessionDto activitySessionDto) {
        ActivitySession activitySession = new ActivitySession();
        activitySession.setId(activitySessionDto.getId());
        activitySession.setTimeSpent(activitySessionDto.getTimeSpent());
        activitySession.setUser(userConverter.convert(activitySessionDto.getUserDto()));
        activitySession.setActivity(activityConverter.convert(activitySessionDto.getActivityDto()));
        activitySession.setCurrentDate(activitySessionDto.getCurrentDate());
        LOGGER.debug("Activity session is converted from activity session dto!");
        return activitySession;
    }
}
