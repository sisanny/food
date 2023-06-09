package com.homework.orderapplication.enumValidator;

import com.homework.orderapplication.model.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Validates that a string value is a member of a specified enum, Status.
 * The enum class must have a method named "getValue"
 * that returns a string representation of the enum value.
 * Valid values are: received, preparing, ready, and delivered.
 */
public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(e -> ((Status) e).getValue().toLowerCase())
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.stream().anyMatch(s -> s.equalsIgnoreCase(value.toString()));
    }
}

