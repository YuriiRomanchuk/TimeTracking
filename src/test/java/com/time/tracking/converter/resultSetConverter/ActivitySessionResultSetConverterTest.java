package com.time.tracking.converter.resultSetConverter;

import com.time.tracking.model.entity.Activity;
import com.time.tracking.model.entity.ActivitySession;
import com.time.tracking.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ActivitySessionResultSetConverterTest {

    @Mock
    private ActivityResultSetConverter activityResultSetConverter;

    @Mock
    private UserResultSetConverter userResultSetConverter;

    @InjectMocks
    private ActivitySessionResultSetConverter activitySessionResultSetConverter;

    @Test
    public void convert() throws SQLException {

        Date date = new Date();
        Activity activity = new Activity();
        User user = new User();

        ResultSet resultSet = Mockito.mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getInt("time_spent")).thenReturn(10);
        when(resultSet.getTimestamp("date")).thenReturn(new Timestamp(date.getTime()));
        when(activityResultSetConverter.convert(resultSet)).thenReturn(activity);
        when(userResultSetConverter.convert(resultSet)).thenReturn(user);

        ActivitySession activitySession = new ActivitySession();
        activitySession.setId(1);
        activitySession.setTimeSpent(10);
        activitySession.setCurrentDate(new Timestamp(date.getTime()));
        activitySession.setActivity(activity);
        activitySession.setUser(user);

        ActivitySession convert = activitySessionResultSetConverter.convert(resultSet);
        Assert.assertTrue(new ReflectionEquals(convert).matches(activitySession));
    }
}
