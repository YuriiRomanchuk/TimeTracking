package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;

@InitializeComponent
public class ActivityConverter implements Converter<ActivityDto, Activity> {

    @Override
    public Activity convert(ActivityDto activityDto) {
        Activity activity = new Activity();
        activity.setId(activityDto.getId());
        activity.setName(activityDto.getName());
        return activity;
    }
}
