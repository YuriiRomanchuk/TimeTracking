package com.time.tracking.validator;

import com.time.tracking.validator.typeValidator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class ModelValidator<T> {

    protected final Map<Validator, Function<T, Object>> validators = new HashMap<>();

    public String validate(T data) {

        List<String> validationErrors = new ArrayList<>();

        validators.forEach((stringValidator, userDtoStringFunction) -> {
            if (!stringValidator.validateValue(userDtoStringFunction.apply(data))) {
                validationErrors.add(stringValidator.getMessage());
            }
        });

        return String.join(",", validationErrors);
    }
}
