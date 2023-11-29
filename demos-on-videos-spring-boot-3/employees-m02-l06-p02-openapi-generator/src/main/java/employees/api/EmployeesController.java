package employees.api;

import employees.CreateEmployeeCommand;
import employees.EmployeeDto;
import employees.EmployeesService;
import employees.UpdateEmployeeCommand;
import employees.model.EmployeeRequest;
import employees.model.EmployeeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api")
public class EmployeesController implements EmployeesApi {

    private EmployeesService employeesService;

    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<EmployeeResponse> createEmployee(EmployeeRequest employeeRequest) {
        CreateEmployeeCommand command = modelMapper.map(employeeRequest, CreateEmployeeCommand.class);
        EmployeeDto employeeDto = employeesService.createEmployee(command);
        return ResponseEntity.ok(modelMapper.map(employeeDto, EmployeeResponse.class));
    }

    @Override
    public ResponseEntity<Void> deleteEmployee(Long id) {
        employeesService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<EmployeeResponse> findEmployeeById(Long id) {
        EmployeeDto employeeDto = employeesService.findEmployeeById(id);
        return ResponseEntity.ok(modelMapper.map(employeeDto, EmployeeResponse.class));
    }

    @Override
    public ResponseEntity<List<EmployeeResponse>> listEmployees(String prefix) {
        List<EmployeeDto> employees = employeesService.listEmployees(Optional.ofNullable(prefix));
        java.lang.reflect.Type targetType = new TypeToken<List<EmployeeResponse>>() {}.getType();
        return ResponseEntity.ok(modelMapper.map(employees, targetType));
    }

    @Override
    public ResponseEntity<EmployeeResponse> updateEmployee(Long id, EmployeeRequest employeeRequest) {
        UpdateEmployeeCommand command = modelMapper.map(employeeRequest, UpdateEmployeeCommand.class);
        EmployeeDto employeeDto = employeesService.updateEmployee(id, command);
        return ResponseEntity.ok(modelMapper.map(employeeDto, EmployeeResponse.class));
    }
}
