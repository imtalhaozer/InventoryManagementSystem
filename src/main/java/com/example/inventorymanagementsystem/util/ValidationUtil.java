package com.example.inventorymanagementsystem.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtil {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = factory.getValidator();

    public static <T> String validate(T object) {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        StringBuilder errorMessage = new StringBuilder("Validation failed: ");

        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                errorMessage.append(violation.getPropertyPath())
                        .append(" - ")
                        .append(violation.getMessage())
                        .append("; ");
            }
        }
        return violations.isEmpty() ? null : errorMessage.toString();
    }
}