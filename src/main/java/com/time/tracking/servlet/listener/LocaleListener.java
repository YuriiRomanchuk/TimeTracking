package com.reservation.controller.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Locale;

public class LocaleListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        if (event.getName().equals("lang")) {
            String locale = String.valueOf(event.getSession().getAttribute("lang"));
            event.getSession().setAttribute("locale",
                    new Locale(locale.substring(0, 2), locale.substring(3, 5)));
        }

    }
}
