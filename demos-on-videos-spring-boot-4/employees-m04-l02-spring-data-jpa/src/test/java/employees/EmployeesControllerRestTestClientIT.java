package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeesControllerRestTestClientIT {

    RestTestClient restTestClient;

    @Autowired
    EmployeesService employeesService;

    @Autowired
    EmployeesController employeesController;

    @BeforeEach
    public void setUp() {
        restTestClient = RestTestClient.bindToController(employeesController).build();
    }

    // @Test
    @RepeatedTest(2)
    void testListEmployees() {
        employeesService.deleteAllEmployees();

        restTestClient
                .post()
                .uri("/api/employees")
                .body(new CreateEmployeeCommand("John Doe"))
                .exchange()
                .expectBody(EmployeeDto.class)
                .value(employeeDto -> assertEquals("John Doe", employeeDto.getName()))
        ;

        restTestClient
                .post()
                .uri("/api/employees")
                .body(new CreateEmployeeCommand("Jane Doe"))
                .exchange()
                .expectBody(EmployeeDto.class)
                .value(employeeDto -> assertEquals("Jane Doe", employeeDto.getName()))
        ;

        restTestClient
                .get()
                        .uri("/api/employees")
                                .exchange()
                .expectBody(new ParameterizedTypeReference<List<EmployeeDto>>() {
                })
                .value(employees -> assertThat(employees).extracting(EmployeeDto::getName).containsExactly("John Doe", "Jane Doe"));

    }

}
