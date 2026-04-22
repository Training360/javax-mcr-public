package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {

    private EmployeesRepository repository;

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return repository.findAll().stream()
                .map(e -> new EmployeeDto(e.getId(), e.getName()))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
        return new EmployeeDto(employee.getId(), employee.getName());
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        repository.save(employee);
        log.info("Employee has been created");
        log.debug("Employee has been created with name {}", command.getName());
        return new EmployeeDto(employee.getId(), employee.getName());
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
        employee.setName(command.getName());
        return new EmployeeDto(employee.getId(), employee.getName());
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    public void deleteAllEmployees() {
        repository.deleteAll();
    }
}
