package employees;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null &&
                !value.isBlank() &&
                value.length() > 2 &&
                value.length() <= maxLength &&
                Character.isUpperCase(value.charAt(0))
                ;
    }

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }
}
