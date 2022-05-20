package locations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class LocationsController {

    private List<Location> locations = Arrays.asList(
            new Location("Budapest", 47.497912, 19.040235),
            new Location("Róma", 41.90383, 12.50557),
            new Location("Athén", 37.97954, 23.72638)
    );

    @GetMapping("/")
    @ResponseBody
    public String getLocations() {
        return locations.toString();
    }
}
