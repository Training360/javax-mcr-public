package training.addresses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddressDto {

    @Schema(example = "Budapest")
    private String city;

    @Schema(example = "Andrássy út 3.")
    private String address;
}
