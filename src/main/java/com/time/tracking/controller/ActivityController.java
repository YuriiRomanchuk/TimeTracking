package com.time.tracking.controller;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.service.ActivityService;

@InitializeComponent
public class ActivityController implements Controller {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }
}
