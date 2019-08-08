package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.exception.ServiceException;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.model.dto.user.UserDto;
import com.time.tracking.model.dto.user.UserLoginDto;
import com.time.tracking.model.entity.User;
import com.time.tracking.model.enums.Role;
import com.time.tracking.service.ActivitySessionService;
import com.time.tracking.service.UserService;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class UserController implements Controller {

    private static final Logger LOGGER = LogManager.getLogger(UserController.class);
    private final UserService userService;
    private final ActivitySessionService activitySessionService;

    public UserController(UserService userService, ActivitySessionService activitySessionService) {
        this.userService = userService;
        this.activitySessionService = activitySessionService;
    }

    @GetMessage("/registration-form")
    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    @GetMessage("/login")
    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    @GetMessage("/logout")
    public View logout() {
        /*  LOGGER.debug("User logout");*/
        return new RedirectView(new ViewModel("index"));
    }

    @PostMessage("/login")
    public View loginUser(UserLoginDto userLoginDto) {
        View view;
        try {
            /*view = validateLoginUser(userDto);*/
            /*  LOGGER.debug("User login");*/
            User user = userService.loginUser(userLoginDto);
            view = receiveViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area", "");
        } catch (ServiceException e) {
            view = receiveViewModel("login", e.getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/registration-form")
    public View createUser(UserCreateDto userCreateDto) {
        View view;
        try {
            userService.createUser(userCreateDto);
            view = receiveViewModel("login", "User created!");
        } catch (Exception e) {
            view = receiveViewModel("registration-form", e.getMessage());
        }
        return new RedirectView(view);
    }

    @GetMessage("/admin-personal-area")
    public View showAdminPersonalArea() {
        View view;
        /*  try {*/
        view = new ViewModel("WEB-INF/jsp/admin/admin-personal-area.jsp");
        /*   view.addParameter("roomsDto", roomService.receiveAllRoomsDto());*/
        /*LOGGER.debug("show admin personal area");*/
     /*   } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
      *//*      LOGGER.debug("Admin personal area is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());*//*
        }*/
        return view;
    }

    @GetMessage("/user-personal-area")
    public View showUserPersonalArea(UserDto userDto) {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/user/user-personal-area.jsp");
            view.addParameter("activitiesSession", activitySessionService.receiveTodayActivities(userDto));
            LOGGER.debug("show admin personal area");
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("User personal area is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
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
