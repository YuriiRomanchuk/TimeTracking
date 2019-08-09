package com.time.tracking.validator.typeValidator;

import com.time.tracking.model.dto.ActivityDto;

public class ActivityDtoValidator implements Validator<ActivityDto> {

    private String message;

    public ActivityDtoValidator(String message) {
        this.message = message;
    }

    @Override
    public boolean validateValue(ActivityDto activityDto) {

        if (activityDto != null) {
            return activityDto.getId() > 0;
        }
        return false;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
