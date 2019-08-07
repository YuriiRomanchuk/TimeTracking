package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ActivityDtoFromEntityConverter implements Converter<Activity, ActivityDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);

    @Override
    public ActivityDto convert(Activity activity) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activity.getId());
        activityDto.setName(activity.getName());
        LOGGER.debug("Activity dto is converted from entity!");
        return activityDto;
    }
}
