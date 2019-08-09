package com.time.tracking.validator;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.model.dto.ActivitySessionDto;
import com.time.tracking.validator.typeValidator.ActivityDtoValidator;
import com.time.tracking.validator.typeValidator.NumberValidator;

import java.util.ResourceBundle;

@InitializeComponent
public class AddActivitySessionValidator extends ModelValidator<ActivitySessionDto> {

    public AddActivitySessionValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        NumberValidator timeSpentValidator = new NumberValidator(resourceBundle.getString("regexNumber"), "Wrong time spent");
        ActivityDtoValidator activityDtoValidator = new ActivityDtoValidator("Wrong activity!");
        validators.put(timeSpentValidator, ActivitySessionDto::getTimeSpent);
        validators.put(activityDtoValidator, ActivitySessionDto::getActivityDto);
    }
}


