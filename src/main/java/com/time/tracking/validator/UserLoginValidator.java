package com.time.tracking.validator;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.model.dto.user.UserLoginDto;
import com.time.tracking.validator.typeValidator.StringValidator;
import java.util.ResourceBundle;

@InitializeComponent
public class UserLoginValidator extends ModelValidator<UserLoginDto> {

    public UserLoginValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator emailValidator = new StringValidator(resourceBundle.getString("regexEmail"), "Wrong email ");
        StringValidator passwordValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong Password");
        validators.put(passwordValidator, UserLoginDto::getPassword);
        validators.put(emailValidator, UserLoginDto::getEmail);
    }
}
