package locations;

import lombok.Data;

@Data
public class UpdateLocationCommand {

    private String name;

    private double latitude;

    private double longitude;
}
