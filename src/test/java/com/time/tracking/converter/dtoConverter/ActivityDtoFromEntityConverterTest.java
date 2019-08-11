package com.time.tracking.converter.dtoConverter;

import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.model.entity.Activity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActivityDtoFromEntityConverterTest {

    @InjectMocks
    private ActivityDtoFromEntityConverter activityDtoFromEntityConverter;

    @Test
    public void convert() {

        Activity activity = new Activity();
        activity.setName("Бег");
        activity.setEnglishName("Running");

        ActivityDto activityDto = new ActivityDto();
        activityDto.setName("Бег");
        activityDto.setEnglishName("Running");

        ActivityDto convert = activityDtoFromEntityConverter.convert(activity);
        Assert.assertTrue(new ReflectionEquals(convert).matches(activityDto));
    }
}
