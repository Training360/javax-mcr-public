package employees;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeesControllerTest {

    @Mock
    EmployeesService employeesService;

    @InjectMocks
    EmployeesController employeesController;

    @Test
    void createEmployee() {
        EmployeeDto result = new EmployeeDto();
        result.setId(1L);
        result.setName("John Doe");
        when(employeesService.createEmployee(any())).thenReturn(result);

        CreateEmployeeCommand command = new CreateEmployeeCommand();
        command.setName("John Doe");
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:8080");

        ResponseEntity<EmployeeDto> response = employeesController.createEmployee(command, builder);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(List.of("http://localhost:8080/api/employees/1"), response.getHeaders().get("Location"));
        assertEquals("John Doe", response.getBody().getName());
    }
}