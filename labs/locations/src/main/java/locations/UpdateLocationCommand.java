package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationCommand {

    // Swagger UI
    @Schema(description = "Name of location: ", example = "Somewhere")
    // Validáció
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Schema(description = "Latitude: ", example = "0.0")
//    @Min(value = -90)
//    @Max(value = 90)
    // Validáció - saját validáció
    @Coordinate
    private double latitude;

    @Schema(description = "Longitude: ",  example = "0.0")
//    @Min(value = -180)
//    @Max(value = 180)
    // Validáció - saját validáció
    @Coordinate(type = Type.LON)
    private double longitude;
}
