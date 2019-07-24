package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

@InitializeComponent
public class WelcomeController implements Controller {

    @GetMessage("/index")
    public View showIndexPage() {
        return new ViewModel("WEB-INF/jsp/index.jsp");
    }
}
