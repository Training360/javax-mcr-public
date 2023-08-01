package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeesControllerWebClientIT {

    @MockBean
    EmployeesService employeesService;

    @Autowired
    WebTestClient webClient;

    @Test
    void testCreateEmployee() {
        when(employeesService.createEmployee(any()))
                .thenReturn(new EmployeeDto(1L, "John Doe"));

        webClient
                .post()
                .uri("/api/employees")
                .bodyValue(new CreateEmployeeCommand("John Doe"))
                .exchange()
                .expectStatus().isCreated()
//                .expectBody(String.class).value(s -> System.out.println(s));
//                .expectBody().jsonPath("name").isEqualTo("John Doe");
                .expectBody(EmployeeDto.class).value(e -> assertEquals("John Doe", e.getName()));
    }

    @Test
    void testFindEmployeeById() {
        when(employeesService.findEmployeeById(1L))
                .thenReturn(new EmployeeDto(1L, "John Doe"));

        webClient.get().uri("/api/employees/{id}", 1)
                .exchange()
                .expectBody(EmployeeDto.class).value(e -> assertEquals("John Doe", e.getName()));
    }

    @Test
    void testListEmployees() {
        when(employeesService.listEmployees(any()))
                .thenReturn(List.of(
                   new EmployeeDto(1L, "John Doe"),
                   new EmployeeDto(2L, "Jack Doe")
                ));

        webClient
                .get()
//                .uri("/api/employees")
                .uri(builder -> builder.path("/api/employees").queryParam("prefix", "J").build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(EmployeeDto.class).hasSize(2).contains(
                        new EmployeeDto(1L, "John Doe")
                );
    }
}
