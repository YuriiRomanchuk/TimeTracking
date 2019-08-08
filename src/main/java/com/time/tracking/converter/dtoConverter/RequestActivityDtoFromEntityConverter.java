package com.time.tracking.converter.dtoConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.entity.RequestActivity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class RequestActivityDtoFromEntityConverter implements Converter<RequestActivity, RequestActivityDto> {

    private static final Logger LOGGER = LogManager.getLogger(UserDtoConverter.class);
    private ActivityDtoFromEntityConverter activityDtoFromEntityConverter;
    private UserDtoFromEntityConverter userDtoFromEntityConverter;

    public RequestActivityDtoFromEntityConverter(ActivityDtoFromEntityConverter activityDtoFromEntityConverter, UserDtoFromEntityConverter userDtoFromEntityConverter) {
        this.activityDtoFromEntityConverter = activityDtoFromEntityConverter;
        this.userDtoFromEntityConverter = userDtoFromEntityConverter;
    }

    @Override
    public RequestActivityDto convert(RequestActivity requestActivity) {
        RequestActivityDto requestActivityDto = new RequestActivityDto();
        requestActivityDto.setId(requestActivity.getId());
        requestActivityDto.setActivityDto(activityDtoFromEntityConverter.convert(requestActivity.getActivity()));
        requestActivityDto.setUserDto(userDtoFromEntityConverter.convert(requestActivity.getUser()));
        requestActivityDto.setDateReview(requestActivity.getDateReview());
        requestActivityDto.setRequestStatus(requestActivity.getRequestStatus());
        requestActivityDto.setRequestAction(requestActivity.getRequestAction());
        LOGGER.debug("Request activity dto is converted from entity!");
        return requestActivityDto;
    }
}
