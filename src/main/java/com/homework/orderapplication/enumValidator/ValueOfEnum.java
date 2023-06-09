package com.homework.orderapplication.enumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * A custom validation annotation that checks whether a string value
 * is one of the values in the specified enum class.
 *
 * @since 1.0
 * @see ValueOfEnumValidator
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Must be any of the following: received, preparing, ready or delivered";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}