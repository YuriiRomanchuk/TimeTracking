package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivityDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class ActivityDtoConverter implements Converter<HttpServletRequest, ActivityDto> {

    private static final Logger LOGGER = LogManager.getLogger(ActivityDtoConverter.class);

    @Override
    public ActivityDto convert(HttpServletRequest request) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(request.getParameter("activity_id") != null & !request.getParameter("activity_id").equals("Enter activities")
                ? Integer.valueOf(request.getParameter("activity_id")) : -1);
        activityDto.setName(request.getParameter("name"));
        LOGGER.debug("Activity dto is converted from request!");
        return activityDto;
    }
}
