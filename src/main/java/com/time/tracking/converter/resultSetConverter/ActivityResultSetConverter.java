package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.converter.Converter;
import com.time.tracking.model.entity.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;

@InitializeComponent
public class ActivityResultSetConverter implements Converter<ResultSet, Activity> {

    @Override
    public Activity convert(ResultSet resultSet) throws SQLException {
        Activity activity = new Activity();
        activity.setId(resultSet.getInt("activity_id"));
        activity.setName(resultSet.getString("activity_name"));
        return activity;
    }
}
