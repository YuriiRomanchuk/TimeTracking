package com.time.tracking.controller;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class ChangeLanguageController implements Controller {

    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageController.class);
    @PostMessage("/change_language")
    public View changeLanguage() {
        LOGGER.debug("Language changed");
        return new RedirectView(new ViewModel("index"));
    }
}
