package employees;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class EmployeesService {

    public static final String EMPLOYEES_CREATED_COUNTER = "employees.created";
    private ModelMapper modelMapper;

    private EmployeesRepository repository;

    private MeterRegistry meterRegistry;

    private ApplicationEventPublisher publisher;

    @PostConstruct
    public void init() {
        Counter.builder(EMPLOYEES_CREATED_COUNTER)
                .baseUnit("employees")
                .description("Numbers of created employees")
                .register(meterRegistry);
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
        return repository.findAll().stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found")),
                EmployeeDto.class);
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        repository.save(employee);
        log.info("Employee has been created");
        log.debug("Employee has been created with name {}", command.getName());
        meterRegistry.counter(EMPLOYEES_CREATED_COUNTER)
                .increment();

        publisher.publishEvent(new AuditApplicationEvent("anonymous",
                "employee_has_been_created",
                Map.of("name", command.getName())));

        return modelMapper.map(employee, EmployeeDto.class);
    }

    @Transactional
    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
        employee.setName(command.getName());
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void deleteEmployee(long id) {
        repository.deleteById(id);
    }

    public void deleteAllEmployees() {
        repository.deleteAll();
    }
}
