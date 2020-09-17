package training.timesheet;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeesService {

    private EmployeesRepository employeesRepository;

    private ModelMapper modelMapper;

    private ApplicationEventPublisher publisher;

    public EmployeesService(EmployeesRepository employeesRepository, ModelMapper modelMapper, ApplicationEventPublisher publisher) {
        this.employeesRepository = employeesRepository;
        this.modelMapper = modelMapper;
        this.publisher = publisher;
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(command.getName());
        employeesRepository.save(employee);
        log.debug("Employee has been created with name: {}", employee.getName());
        publisher.publishEvent(modelMapper.map(employee, EmployeeHasCreatedEvent.class));
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<EmployeeDto> listEmployees() {
        return employeesRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employeesRepository.findById(id), EmployeeDto.class);
    }

}
