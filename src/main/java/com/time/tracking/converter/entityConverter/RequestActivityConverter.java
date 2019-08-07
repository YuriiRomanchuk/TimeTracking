package com.time.tracking.converter.entityConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.dto.RequestActivityDto;
import com.time.tracking.model.entity.RequestActivity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@InitializeComponent
public class RequestActivityConverter implements Converter<RequestActivityDto, RequestActivity> {

    private static final Logger LOGGER = LogManager.getLogger(ActivityConverter.class);

    private final ActivityConverter activityConverter;
    private final UserConverter userConverter;

    public RequestActivityConverter(ActivityConverter activityConverter, UserConverter userConverter) {
        this.activityConverter = activityConverter;
        this.userConverter = userConverter;
    }

    @Override
    public RequestActivity convert(RequestActivityDto result) throws Exception {
        RequestActivity requestActivity = new RequestActivity();
        requestActivity.setId(result.getId());
        requestActivity.setUser(userConverter.convert(result.getUserDto()));
        requestActivity.setActivity(activityConverter.convert(result.getActivityDto()));
        requestActivity.setRequestStatus(result.getRequestStatus());
        requestActivity.setRequestAction(result.getRequestAction());
        requestActivity.setDateReview(result.getDateReview());
        LOGGER.debug("Activity is converted from activity dto!");
        return requestActivity;
    }
}
