package com.time.tracking.controller;

import com.time.tracking.config.annotation.GetMessage;
import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class WelcomeController implements Controller {

    private static final Logger LOGGER = LogManager.getLogger(WelcomeController.class);
    @GetMessage("/index")
    public View showIndexPage() {
        return new ViewModel("WEB-INF/jsp/index.jsp");
    }
}
