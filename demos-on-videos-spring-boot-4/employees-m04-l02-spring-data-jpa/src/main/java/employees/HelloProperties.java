package employees;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;

@Data
@ConfigurationProperties(prefix = "employees")
@Validated
public class HelloProperties {

    @NotBlank
    private String message;
}
