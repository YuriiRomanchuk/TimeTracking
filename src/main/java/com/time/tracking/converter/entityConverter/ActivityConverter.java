package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ActivityConverter implements Converter<ActivityDto, Activity> {

    private static final Logger LOGGER = LogManager.getLogger(ActivityConverter.class);

    @Override
    public Activity convert(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setName(activityDto.getName());
        LOGGER.debug("Activity is converted from activity dto!");
        return activity;
    }


}
