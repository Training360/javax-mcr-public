package employees;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Violation {

    private String name;

    private String message;
}
