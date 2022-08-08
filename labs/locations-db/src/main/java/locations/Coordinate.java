package locations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CoordinateValidator.class)
public @interface Coordinate {

    String message() default "Invalid latitude (-90.0 < latitude < 90.0) or longitude (-180.0 < longitude < 180.0).";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Type type() default Type.LAT;
}
