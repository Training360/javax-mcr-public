package employees;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private ModelMapper modelMapper;

    private List<Employee> employees = Collections.synchronizedList(new ArrayList<>(List.of(
        new Employee(1L, "John Doe"),
        new Employee(2L, "Jack Doe"),
        new Employee(2L, "Jack Smith")
    )));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees(QueryParameters queryParameters) {
        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();
        List<Employee> filtered = employees.stream()
                .filter(e -> queryParameters.getPrefix() == null || e.getName().toLowerCase().startsWith(queryParameters.getPrefix().toLowerCase()))
                .filter(e -> queryParameters.getSuffix() == null || e.getName().toLowerCase().endsWith(queryParameters.getSuffix().toLowerCase()))
                .collect(Collectors.toList());
        return modelMapper.map(filtered, targetListType);
    }

    public EmployeeDto findEmployeeById(long id) {
        return modelMapper.map(employees.stream()
                .filter(e -> e.getId() == id).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id)),
                EmployeeDto.class);
    }
}
