package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.model.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivityResultSetConverterTest {

    @InjectMocks
    private ActivityResultSetConverter activityResultSetConverter;

    @Test
    public void convert() throws SQLException {
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("activity_id")).thenReturn(1);
        when(resultSet.getString("activity_name")).thenReturn("Бег");
        when(resultSet.getString("activity_english_name")).thenReturn("Running");

        Activity activity = new Activity();
        activity.setId(1);
        activity.setName("Бег");
        activity.setEnglishName("Running");

        Activity convert = activityResultSetConverter.convert(resultSet);
        Assert.assertTrue(new ReflectionEquals(convert).matches(activity));
    }
}
