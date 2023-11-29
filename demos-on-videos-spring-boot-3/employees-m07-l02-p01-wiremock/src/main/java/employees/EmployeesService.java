package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper;

    private EmployeesRepository repository;

    private AddressesGateway addressesGateway;

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

    public AddressDto findAddressById(long id) {
        Employee employee = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));
        return addressesGateway.findAddressByName(employee.getName());
    }
}
