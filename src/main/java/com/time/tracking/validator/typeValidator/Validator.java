package com.time.tracking.validator.typeValidator;

public interface Validator<T> {

    boolean validateValue(T value);

    String getMessage();

}
