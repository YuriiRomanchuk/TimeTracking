package com.time.tracking.validator;

import com.time.tracking.config.annotation.InitializeComponent;
import com.time.tracking.model.dto.user.UserCreateDto;
import com.time.tracking.validator.typeValidator.StringValidator;

import java.util.ResourceBundle;

@InitializeComponent
public class UserRegistrationDataValidator extends ModelValidator<UserCreateDto> {

    public UserRegistrationDataValidator() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("regexpValidator");
        StringValidator emailValidator = new StringValidator(resourceBundle.getString("regexEmail"), "Wrong email");
        StringValidator firstNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong first name");
        StringValidator lastNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong last name ");
        StringValidator middleNameValidator = new StringValidator(resourceBundle.getString("regexString"), "Wrong middle name");
        StringValidator nicknameValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong nickname ");
        StringValidator passwordValidator = new StringValidator(resourceBundle.getString("regexStringNumber"), "Wrong Password");
        StringValidator phoneValidator = new StringValidator(resourceBundle.getString("regexPhoneNumber"), "Wrong phone");

        validators.put(passwordValidator, UserCreateDto::getPassword);
        validators.put(emailValidator, UserCreateDto::getEmail);
        validators.put(firstNameValidator, UserCreateDto::getFirstName);
        validators.put(lastNameValidator, UserCreateDto::getLastName);
        validators.put(middleNameValidator, UserCreateDto::getMiddleName);
        validators.put(nicknameValidator, UserCreateDto::getLogin);
        validators.put(phoneValidator, UserCreateDto::getPhone);
    }
}
