package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.service.ActivityService;
import com.time.tracking.service.ActivitySessionService;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

@InitializeComponent
public class ActivitySessionController implements Controller {

    private final ActivitySessionService requestActivityService;
    private final ActivityService activityService;

    public ActivitySessionController(ActivitySessionService requestActivityService, ActivityService activityService) {
        this.requestActivityService = requestActivityService;
        this.activityService = activityService;
    }

    @GetMessage("/user-add-activity-session")
    public View showAddRequestActivityPage(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-add-activity-session.jsp");
            view.addParameter("activities", activityService.receiveFreeActivitiesForUser(userDto.getId()));
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/user-add-activity-session")
    public View addRequestActivity(ActivitySessionDto activitySessionDto) {
        View view;
        try {
            requestActivityService.addActivitySession(activitySessionDto);
            view = receiveViewModel("user-personal-area", "Activity session created!");
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }

}
