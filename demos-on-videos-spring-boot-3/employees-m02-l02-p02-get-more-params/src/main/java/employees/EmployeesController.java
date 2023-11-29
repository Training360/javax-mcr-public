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
    public List<EmployeeDto> listEmployees(QueryParameters queryParameters) {
        return employeesService.listEmployees(queryParameters);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }

}
