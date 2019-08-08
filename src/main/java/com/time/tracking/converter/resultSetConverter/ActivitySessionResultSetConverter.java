package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.converter.Converter;
import com.time.tracking.model.entity.ActivitySession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivitySessionResultSetConverter implements Converter<ResultSet, ActivitySession> {

    private static final Logger LOGGER = LogManager.getLogger(ActivitySessionResultSetConverter.class);
    private final ActivityResultSetConverter activityResultSetConverter;
    private final UserResultSetConverter userResultSetConverter;

    public ActivitySessionResultSetConverter(ActivityResultSetConverter activityResultSetConverter, UserResultSetConverter userResultSetConverter) {
        this.activityResultSetConverter = activityResultSetConverter;
        this.userResultSetConverter = userResultSetConverter;
    }

    @Override
    public ActivitySession convert(ResultSet resultSet) throws SQLException {
        ActivitySession activitySession = new ActivitySession();
        activitySession.setId(resultSet.getInt("id"));
        activitySession.setTimeSpent(resultSet.getInt("time_spent"));
        activitySession.setActivity(activityResultSetConverter.convert(resultSet));
        activitySession.setUser(userResultSetConverter.convert(resultSet));
        LOGGER.debug("Activity session result set is converted!");
        return activitySession;
    }
}
