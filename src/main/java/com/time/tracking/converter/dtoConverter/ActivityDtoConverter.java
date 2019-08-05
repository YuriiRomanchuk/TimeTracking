package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.ActivityDto;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class ActivityDtoConverter implements Converter<HttpServletRequest, ActivityDto> {

    @Override
    public ActivityDto convert(HttpServletRequest request) {
        ActivityDto activityDto = new ActivityDto();
        activityDto.setName(request.getParameter("name"));
        return activityDto;
    }
}
