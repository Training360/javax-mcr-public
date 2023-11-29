package employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.UUID;

@ControllerAdvice
public class EmployeesExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ProblemDetail handleNotFoundException(EmployeeNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                e.getMessage());
        problemDetail.setType(URI.create("employees/not-found"));
        problemDetail.setTitle("Not found");
        problemDetail.setProperty("id", UUID.randomUUID().toString());
        return problemDetail;
    }

}
