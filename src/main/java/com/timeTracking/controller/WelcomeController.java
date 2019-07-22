package com.timeTracking.controller;

import com.timeTracking.config.annotation.InitializeComponent;
import com.timeTracking.config.annotation.GetMessage;
import view.View;
import view.ViewModel;

@InitializeComponent
public class WelcomeController implements Controller {

    @GetMessage("/index")
    public View showIndexPage() {
        return new ViewModel("WEB-INF/jsp/index.jsp");
    }
}
