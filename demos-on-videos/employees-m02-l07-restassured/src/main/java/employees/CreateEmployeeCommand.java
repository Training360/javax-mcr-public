package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeCommand {

    @Schema(description = "name of the employee", example = "John Doe")
    private String name;
}
