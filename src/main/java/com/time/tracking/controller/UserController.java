package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.model.dto.UserDto;
import com.time.tracking.service.UserService;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

@InitializeComponent
public class UserController implements Controller {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMessage("/registration-form")
    public View showRegistrationPage() {
        return new ViewModel("WEB-INF/jsp/registration-form.jsp");
    }

    @GetMessage("/login")
    public View showUserLoginPage() {
        return new ViewModel("WEB-INF/jsp/login.jsp");
    }

    @PostMessage("/registration-form")
    public View createUser(UserDto userDto) {
        View view;
        try {
            userService.createUser(userDto);
            view = receiveViewModel("login", "User created!");
        } catch (Exception e) {
            view = receiveViewModel("registration-form", e.getMessage());
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
