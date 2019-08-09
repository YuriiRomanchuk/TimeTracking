package com.time.tracking.validator.typeValidator;

public class StringValidator implements Validator<String> {

    private String regex;
    private String message;

    public StringValidator(String regex, String message) {
        this.regex = regex;
        this.message = message;
    }

    @Override
    public boolean validateValue(String value) {
        return value.matches(regex);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
