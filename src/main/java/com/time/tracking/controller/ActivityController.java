package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.service.ActivityService;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

@InitializeComponent
public class ActivityController implements Controller {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMessage("/admin-activity")
    public View createActivity(ActivityDto activityDto) {
        View view;
        try {
            activityService.createActivity(activityDto);
            view = receiveViewModel("admin-personal-area", "Room added!");
        } catch (Exception e) {
            view = receiveViewModel("admin-activity", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @GetMessage("/admin-activity")
    public View showActivityPage() {
        View view = new ViewModel("WEB-INF/jsp/admin/admin-activity.jsp");
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }
}
