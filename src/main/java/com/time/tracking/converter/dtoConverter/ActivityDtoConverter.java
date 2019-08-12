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

        String activity_id = request.getParameter("activity_id");

        if (activity_id != null) {
            if (!activity_id.equals("Enter activities")) {
                activityDto.setId(Integer.valueOf(activity_id));
            } else {
                activityDto.setId(-1);
            }
        }
        activityDto.setName(request.getParameter("name"));
        activityDto.setEnglishName(request.getParameter("english_name"));
        LOGGER.debug("Activity dto is converted from request!");
        return activityDto;
    }
}
