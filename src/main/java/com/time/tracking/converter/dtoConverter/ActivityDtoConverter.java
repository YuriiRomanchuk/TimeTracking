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
        activityDto.setId(Integer.valueOf(request.getParameter("activity_id")));
        activityDto.setName(request.getParameter("name"));
        return activityDto;
    }

    public ActivityDto convertFromRequestForActivityId(HttpServletRequest request) {
        int activityId = (Integer) request.getSession().getAttribute("activityId");
        LOGGER.debug("User dto is converted from id!");
        return convertByActivityId(activityId);
    }

    public ActivityDto convertByActivityId(int activityId) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setId(activityId);
        LOGGER.debug("Activity dto is converted from id!");
        return activityDto;
    }
}
