package employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(statements = "delete from employees")
public class EmployeesRepositoryIT {

    @Autowired
    EmployeesRepository repository;

    @Test
    void testPersist() {
        Employee employee = new Employee("John Doe");
        repository.save(employee);
        List<Employee> employees = repository.findAll();

        assertThat(employees)
                .extracting(Employee::getName)
                .containsExactly("John Doe");
    }
}
