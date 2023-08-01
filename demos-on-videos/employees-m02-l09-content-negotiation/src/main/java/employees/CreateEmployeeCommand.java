package employees;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class CreateEmployeeCommand {

    @Schema(description = "name of the employee", example = "John Doe")
    private String name;
}
