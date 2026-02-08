package de.stoll.nicolas.bgraph.person.application.port.in;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PersonIdValidator implements ConstraintValidator<ValidPersonId, String> {

    private static final Pattern PATTERN =
            Pattern.compile("^P-[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isBlank()) {
            return false;
        }
        return PATTERN.matcher(s).matches();
    }
}
