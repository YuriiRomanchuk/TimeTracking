package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivitySessionDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@InitializeComponent
public class ActivitySessionDtoConverter implements Converter<HttpServletRequest, ActivitySessionDto> {

    private static final Logger LOGGER = LogManager.getLogger(ActivitySessionDtoConverter.class);
    private final UserDtoConverter userDtoConverter;
    private final ActivityDtoConverter activityDtoConverter;

    public ActivitySessionDtoConverter(UserDtoConverter userDtoConverter, ActivityDtoConverter activityDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.activityDtoConverter = activityDtoConverter;
    }

    @Override
    public ActivitySessionDto convert(HttpServletRequest request) {
        ActivitySessionDto activitySessionDto = new ActivitySessionDto();
        activitySessionDto.setActivityDto(activityDtoConverter.convert(request));
        activitySessionDto.setUserDto(userDtoConverter.convert(request));
        activitySessionDto.setCurrentDate(new Date());
        activitySessionDto.setTimeSpent(Integer.valueOf(request.getParameter("time_spent")));
        LOGGER.debug("Activity session dto is converted from request!");
        return activitySessionDto;
    }
}
