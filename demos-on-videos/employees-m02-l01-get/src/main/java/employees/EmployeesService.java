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
        new Employee(2L, "Jack Doe")
    )));

    public EmployeesService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<EmployeeDto> listEmployees() {
        Type targetListType = new TypeToken<List<EmployeeDto>>(){}.getType();
        return modelMapper.map(employees, targetListType);
    }

}
