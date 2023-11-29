package employees;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable("id") long id) {
        try {
            EmployeeDto employeeDto = employeesService.findEmployeeById(id);
            return ResponseEntity
                    .ok()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .body(employeeDto);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity
                    .notFound()
                    .header("Response-Id", UUID.randomUUID().toString())
                    .build();
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody CreateEmployeeCommand command,
                                                      UriComponentsBuilder uri) {
        EmployeeDto employeeDto = employeesService.createEmployee(command);
        return ResponseEntity
                .created(uri.path("/api/employees/{id}").buildAndExpand(employeeDto.getId()).toUri())
                .body(employeeDto);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }

}
