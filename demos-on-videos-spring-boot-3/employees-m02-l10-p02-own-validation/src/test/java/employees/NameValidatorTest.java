package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.validation.ConstraintValidatorContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class NameValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @Name(maxLength = 10)
    private String name;

    @Test
    void isValidTrue() throws NoSuchFieldException {
        NameValidator validator = new NameValidator();
        // Reflection
        validator.initialize(NameValidatorTest.class.getDeclaredField("name").getAnnotation(Name.class));

        assertTrue(validator.isValid("John Doe", context));
    }

    @Test
    void isValid() throws NoSuchFieldException {
        NameValidator validator = new NameValidator();
        // Reflection
        validator.initialize(NameValidatorTest.class.getDeclaredField("name").getAnnotation(Name.class));

        assertFalse(validator.isValid("John Doe John Doe", context));
    }


}