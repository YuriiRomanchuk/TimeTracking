package com.reservation.controller;


import com.reservation.view.RedirectViewModel;
import com.reservation.view.View;
import com.reservation.view.ViewModel;

public class ChangeLanguageController {

    public View changeLanguage() {
        return new RedirectViewModel(new ViewModel("index"));
    }

}
