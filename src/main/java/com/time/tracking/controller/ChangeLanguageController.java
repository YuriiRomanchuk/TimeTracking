package com.time.tracking.controller;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.config.annotation.PostMessage;
import com.time.tracking.view.RedirectView;
import com.time.tracking.view.View;
import com.time.tracking.view.ViewModel;

@InitializeComponent
public class ChangeLanguageController implements Controller {

    @PostMessage("/change_language")
    public View changeLanguage() {
        return new RedirectView(new ViewModel("index"));
    }
}
