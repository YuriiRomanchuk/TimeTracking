package com.time.tracking.validator;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.model.dto.ActivityDto;
import com.time.tracking.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

@InitializeComponent
public class AddActivityValidator extends ModelValidator<ActivityDto> {

    public AddActivityValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator nameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong activity name");
        validators.put(nameValidator, ActivityDto::getName);
    }
}
