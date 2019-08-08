package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.model.entity.ActivitySession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ActivitySessionDtoFromEntityConverter implements Converter<ActivitySession, ActivitySessionDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);
    private ActivityDtoFromEntityConverter activityDtoFromEntityConverter;
    private UserDtoFromEntityConverter userDtoFromEntityConverter;

    public ActivitySessionDtoFromEntityConverter(ActivityDtoFromEntityConverter activityDtoFromEntityConverter, UserDtoFromEntityConverter userDtoFromEntityConverter) {
        this.activityDtoFromEntityConverter = activityDtoFromEntityConverter;
        this.userDtoFromEntityConverter = userDtoFromEntityConverter;
    }

    @Override
    public ActivitySessionDto convert(ActivitySession activitySession) {
        ActivitySessionDto activitySessionDto = new ActivitySessionDto();
        activitySessionDto.setId(activitySession.getId());
        activitySessionDto.setActivityDto(activityDtoFromEntityConverter.convert(activitySession.getActivity()));
        activitySessionDto.setUserDto(userDtoFromEntityConverter.convert(activitySession.getUser()));
        activitySessionDto.setTimeSpent(activitySession.getTimeSpent());
        LOGGER.debug("Activity session dto is converted from entity!");
        return activitySessionDto;
    }

}
