package locations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CoordinateValidator implements ConstraintValidator<Coordinate, Double> {

    private Type type;

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        if (this.type == Type.LAT) {
            return value > -90.0 && value < 90.0;
        } else {
            return value > -180.0 && value < 180.0;
        }
    }

    @Override
    public void initialize(Coordinate constraintAnnotation) {
        this.type = constraintAnnotation.type();
    }
}
