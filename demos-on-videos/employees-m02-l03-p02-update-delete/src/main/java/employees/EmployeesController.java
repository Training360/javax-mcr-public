package employees;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeesService.updateEmployee(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeesService.deleteEmployee(id);
    }
}
