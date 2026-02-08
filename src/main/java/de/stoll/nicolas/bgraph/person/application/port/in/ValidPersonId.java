package de.stoll.nicolas.bgraph.person.application.port.in;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PersonIdValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPersonId {

    String message() default "invalid person id (expected format: P-<uuid>)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
