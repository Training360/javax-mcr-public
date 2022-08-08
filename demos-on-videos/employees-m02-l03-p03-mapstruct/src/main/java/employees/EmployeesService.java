package employees;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

//    private ModelMapper modelMapper;

    private EmployeeMapper employeeMapper;

    private AtomicLong idGenerator = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
        new Employee(idGenerator.incrementAndGet(), "John Doe"),
        new Employee(idGenerator.incrementAndGet(), "Jack Doe")
    )));

//    public EmployeesService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }


    public EmployeesService(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    public List<EmployeeDto> listEmployees(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();
        List<Employee> filtered = employees.stream()
                .filter(e -> prefix.isEmpty() || e.getName().toLowerCase().startsWith(prefix.get().toLowerCase()))
                .collect(Collectors.toList());
//        return modelMapper.map(filtered, targetListType);
        return employeeMapper.toDto(filtered);
    }

    public EmployeeDto findEmployeeById(long id) {
//        return modelMapper.map(employees.stream()
        return employeeMapper.toDto(employees.stream()
                .filter(e -> e.getId() == id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)));
    }

    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        Employee employee = new Employee(idGenerator.incrementAndGet(), command.getName());
        employees.add(employee);
//        return modelMapper.map(employee, EmployeeDto.class);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto updateEmployee(long id, UpdateEmployeeCommand command) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employee.setName(command.getName());
//        return modelMapper.map(employee, EmployeeDto.class);
        return employeeMapper.toDto(employee);
    }

    public void deleteEmployee(long id) {
        Employee employee = employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        employees.remove(employee);
    }
}
