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
import com.time.tracking.validator.UserLoginValidator;
import com.time.tracking.validator.UserRegistrationDataValidator;
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
    private final UserRegistrationDataValidator userRegistrationDataValidator;
    private final UserLoginValidator userLoginValidator;

    public UserController(UserService userService,
                          ActivitySessionService activitySessionService,
                          UserRegistrationDataValidator userRegistrationDataValidator,
                          UserLoginValidator userLoginValidator) {
        this.userService = userService;
        this.activitySessionService = activitySessionService;
        this.userRegistrationDataValidator = userRegistrationDataValidator;
        this.userLoginValidator = userLoginValidator;
    }

    @GetMessage("/registration-form")
    public View showRegistrationPage() {
        LOGGER.debug("show registration form");
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    @GetMessage("/login")
    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    @GetMessage("/logout")
    public View logout() {
        LOGGER.debug("User logout");
        return new RedirectView(new ViewModel("index"));
    }

    @PostMessage("/login")
    public View loginUser(UserLoginDto userLoginDto) {
        View view;
        try {
            view = validateLoginUser(userLoginDto);
            LOGGER.debug("User login");
        } catch (ServiceException e) {
            view = receiveViewModel("login", e.getMessage());
            LOGGER.debug("User is not login" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @PostMessage("/registration-form")
    public View createUser(UserCreateDto userCreateDto) {
        View view;
        try {
            view = validateRegistrationUser(userCreateDto);
            LOGGER.debug("User create");
        } catch (Exception e) {
            view = receiveViewModel("registration-form", e.getMessage());
            LOGGER.debug("User is not created" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
    }

    @GetMessage("/admin-personal-area")
    public View showAdminPersonalArea() {
        View view;
        try {
            view = new ViewModel("WEB-INF/jsp/admin/admin-personal-area.jsp");
            view.addParameter("users", userService.receiveAllUsers());
            LOGGER.debug("show admin personal area");
            return view;
        } catch (ServiceException e) {
            view = receiveViewModel("index", e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
            LOGGER.debug("Admin personal area is not shown!" + e.getCause() == null ? e.getMessage() : e.getCause().getMessage());
        }
        return new RedirectView(view);
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

    private View validateLoginUser(UserLoginDto userLoginDto) throws ServiceException {
        View view;
        String invalidateFields = userLoginValidator.validate(userLoginDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("login", invalidateFields);
        } else {
            User user = userService.loginUser(userLoginDto);
            view = receiveViewModel(user.getRole().equals(Role.ADMIN) ? "admin-personal-area" : "user-personal-area", "");
        }
        return view;
    }

    private View validateRegistrationUser(UserCreateDto userCreateDto) throws ServiceException {
        View view;
        String invalidateFields = userRegistrationDataValidator.validate(userCreateDto);
        if (!invalidateFields.isEmpty()) {
            view = receiveViewModel("registration-form", invalidateFields);
            view.addParameter("userDto", userCreateDto);
        } else {
            userService.createUser(userCreateDto);
            view = receiveViewModel("login", "User created!");
        }
        return view;
    }
}
