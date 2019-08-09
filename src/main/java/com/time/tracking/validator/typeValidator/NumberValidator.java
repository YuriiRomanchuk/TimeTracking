package com.time.tracking.validator.typeValidator;

public class NumberValidator implements Validator<Integer> {

    private String regex;
    private String message;

    public NumberValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public boolean validateValue(Integer value) {
        return String.valueOf(value).matches(regex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
