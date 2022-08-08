package locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "Name of location: ", example = "Somewhere")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Schema(description = "Latitude: ", example = "0.0")
    @Coordinate
    private double latitude;

    @Schema(description = "Longitude: ",  example = "0.0")
    @Coordinate(type = Type.LON)
    private double longitude;
}
