package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.service.ActivityService;
import com.time.tracking.service.ActivitySessionService;
import com.time.tracking.validator.AddActivitySessionValidator;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ActivitySessionController implements Controller {

    private static final Logger LOGGER = LogManager.getLogger(ActivitySessionController.class);
    private final ActivitySessionService requestActivityService;
    private final ActivityService activityService;
    private final AddActivitySessionValidator addActivitySessionValidator;

    public ActivitySessionController(ActivitySessionService requestActivityService,
                                     ActivityService activityService,
                                     AddActivitySessionValidator addActivitySessionValidator) {
        this.requestActivityService = requestActivityService;
        this.activityService = activityService;
        this.addActivitySessionValidator = addActivitySessionValidator;
    }

    @GetMessage("/user-add-activity-session")
    public View showAddRequestActivityPage(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-add-activity-session.jsp");
            view.addParameter("activities", activityService.receiveBusyActivitiesForUser(userDto.getId()));
            LOGGER.debug("Activity session opened");
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Activity session is not shown" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/user-add-activity-session")
    public View addRequestActivity(ActivitySessionDto activitySessionDto) {
        View view;
        String invalidateFields = addActivitySessionValidator.validate(activitySessionDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("user-add-activity-session", invalidateFields);
            view.addParameter("activityDto", activitySessionDto);
            LOGGER.debug("Activity session added");
        } else {
            try {
                requestActivityService.addActivitySession(activitySessionDto);
                view = receiveViewModel("user-personal-area", "Activity session created!");
            } catch (ServiceException e) {
                view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
                LOGGER.debug("Activity session is not added" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            }
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
