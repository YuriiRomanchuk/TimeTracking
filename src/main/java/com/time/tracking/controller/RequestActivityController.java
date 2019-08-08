package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.enums.RequestAction;
import com.time.tracking.model.enums.RequestStatus;
import com.time.tracking.service.ActivityService;
import com.time.tracking.service.RequestActivityService;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

import java.util.Date;

@InitializeComponent
public class RequestActivityController implements Controller {

    private final RequestActivityService requestActivityService;
    private final ActivityService activityService;

    public RequestActivityController(RequestActivityService requestActivityService, ActivityService activityService) {
        this.requestActivityService = requestActivityService;
        this.activityService = activityService;
    }

    @GetMessage("/user-add-request-activity")
    public View showRequestActivityPage(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-add-request-activity.jsp");
            view.addParameter("activities", activityService.receiveFreeActivitiesForUser(userDto.getId()));
            view.addParameter("requestAction", RequestAction.values());
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/user-add-request-activity")
    public View addRequestActivity(RequestActivityDto requestActivityDto) {
        View view;
        try {
            requestActivityService.addRequestActivity(requestActivityDto);
            view = receiveViewModel("user-personal-area", "Request activity created!");
        } catch (ServiceException e) {
            view = receiveViewModel("user-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @GetMessage("/admin-approval-request-activity")
    public View showApproveRequestActivityPage() {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-approval-request-activity.jsp");
            view.addParameter("requestActivities", requestActivityService.receiveFreeActivitiesForUser());
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("admin-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/approve-request")
    public View approveRequestRoom(RequestActivityDto requestActivityDto) {
        return new RedirectView(changeRequestActivityStatus(requestActivityDto.getId(), RequestStatus.APPROVE));
    }

    @PostMessage("/reject-request")
    public View rejectRequestRoom(RequestActivityDto requestActivityDto) {
        return new RedirectView(changeRequestActivityStatus(requestActivityDto.getId(), RequestStatus.REJECT));
    }

    private View changeRequestActivityStatus(int requestActivityId, RequestStatus requestStatus) {
        View view;
        try {
            requestActivityService.changeRequestRoomStatus(requestActivityId, requestStatus, new Date());
            view = new ViewModel("admin-approval-request-activity");
        } catch (ServiceException e) {
            view = receiveViewModel("admin-personal-area", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return view;
    }

    private View receiveViewModel(String path, String error) {
        View view;
        view = new ViewModel(path);
        view.addParameter("Error", error);
        return view;
    }


}
