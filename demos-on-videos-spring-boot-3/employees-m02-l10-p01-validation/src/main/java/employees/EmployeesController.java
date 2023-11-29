package employees;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Operations on employees")
public class EmployeesController {

    private EmployeesService employeesService;

    public EmployeesController(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeesService.listEmployees(prefix);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeesService.findEmployeeById(id);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity findEmployeeById(@PathVariable("id") long id) {
//        try {
//            return ResponseEntity.ok(employeesService.findEmployeeById(id));
//        }
//        catch (IllegalArgumentException iea) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "creates an employee")
    @ApiResponse(responseCode = "201", description = "employee has been created")
    public EmployeeDto createEmployee(@Valid @RequestBody CreateEmployeeCommand command) {
        return employeesService.createEmployee(command);
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
