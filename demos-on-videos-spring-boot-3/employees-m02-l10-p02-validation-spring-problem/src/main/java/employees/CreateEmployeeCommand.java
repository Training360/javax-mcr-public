package employees;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeCommand {

    @NotBlank
    private String name;
}
