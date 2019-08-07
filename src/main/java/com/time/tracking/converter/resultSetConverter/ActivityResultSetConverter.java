package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.entity.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

@InitializeComponent
public class ActivityResultSetConverter implements Converter<ResultSet, Activity> {

    private static final Logger LOGGER = LogManager.getLogger(ActivityResultSetConverter.class);

    @Override
    public Activity convert(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getInt("activity_id"));
        activity.setName(resultSet.getString("activity_name"));
        LOGGER.debug("Activity result set is converted!");
        return activity;
    }
}
