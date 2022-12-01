package employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.UUID;

@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ProblemDetail handleNotFoundException(EmployeeNotFoundException nfe) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                String.format(nfe.getMessage()));
        problemDetail.setType(URI.create("employees/employee-not-found"));
        problemDetail.setTitle("Not found");
        problemDetail.setProperty("id", UUID.randomUUID().toString());
        return problemDetail;
    }
}
