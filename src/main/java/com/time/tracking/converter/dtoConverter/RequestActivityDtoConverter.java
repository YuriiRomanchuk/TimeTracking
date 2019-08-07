package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.enums.RequestAction;
import com.time.tracking.model.enums.RequestStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

@InitializeComponent
public class RequestActivityDtoConverter implements Converter<HttpServletRequest, RequestActivityDto> {

    private static final Logger LOGGER = LogManager.getLogger(RequestActivityDto.class);
    private UserDtoConverter userDtoConverter;
    private ActivityDtoConverter activityDtoConverter;

    public RequestActivityDtoConverter(UserDtoConverter userDtoConverter, ActivityDtoConverter activityDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.activityDtoConverter = activityDtoConverter;
    }

    @Override
    public RequestActivityDto convert(HttpServletRequest request) throws Exception {
        RequestActivityDto requestActivityDto = new RequestActivityDto();
        requestActivityDto.setUserDto(userDtoConverter.convertFromRequestForUserId(request));
        requestActivityDto.setActivityDto(activityDtoConverter.convert(request));
        requestActivityDto.setRequestAction(RequestAction.valueOf(request.getParameter("requestAction")));
        requestActivityDto.setRequestStatus(RequestStatus.NEW);
        LOGGER.debug("Request activity dto is converted");
        return requestActivityDto;
    }
}
