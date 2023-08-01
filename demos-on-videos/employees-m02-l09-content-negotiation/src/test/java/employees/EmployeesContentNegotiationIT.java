package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeesContentNegotiationIT {

    @Autowired
    EmployeesService employeesService;

    @Autowired
    WebTestClient webClient;

    @BeforeEach
    void init() {
        employeesService.deleteAllEmployees();
    }

    @Test
    void createThenListEmployees() {
        webClient
                .post()
                .uri("/api/employees")
                .bodyValue(new CreateEmployeeCommand("árvíztűrő tükörfúrógép"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(EmployeeDto.class).value(e -> assertEquals("árvíztűrő tükörfúrógép", e.getName()));

        webClient
                .get()
                .uri("/api/employees")
                .exchange()
                .expectStatus().isOk()
                .expectBody(EmployeesDto.class).value(
                        employees -> assertThat(employees.getEmployees()).extracting(EmployeeDto::getName).containsExactly("árvíztűrő tükörfúrógép")
                );

    }
}
