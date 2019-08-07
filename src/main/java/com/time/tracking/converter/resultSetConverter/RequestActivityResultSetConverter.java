package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.entity.RequestActivity;
import com.time.tracking.model.enums.RequestAction;
import com.time.tracking.model.enums.RequestStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;

@InitializeComponent
public class RequestActivityResultSetConverter implements Converter<ResultSet, RequestActivity> {

    private static final Logger LOGGER = LogManager.getLogger(UserResultSetConverter.class);
    private final ActivityResultSetConverter activityResultSetConverter;
    private final UserResultSetConverter userResultSetConverter;

    public RequestActivityResultSetConverter(ActivityResultSetConverter activityResultSetConverter, UserResultSetConverter userResultSetConverter) {
        this.activityResultSetConverter = activityResultSetConverter;
        this.userResultSetConverter = userResultSetConverter;
    }

    @Override
    public RequestActivity convert(ResultSet resultSet) throws Exception {
        RequestActivity requestActivity = new RequestActivity();
        requestActivity.setId(resultSet.getInt("id "));
        requestActivity.setActivity(activityResultSetConverter.convert(resultSet));
        requestActivity.setUser(userResultSetConverter.convert(resultSet));
        requestActivity.setDateReview(resultSet.getTimestamp("date_review"));
        requestActivity.setRequestAction(RequestAction.valueOf(resultSet.getString("request_action")));
        requestActivity.setRequestStatus(RequestStatus.valueOf(resultSet.getString("request_status")));
        LOGGER.debug("Request activity result set is converted!");
        return requestActivity;
    }
}
