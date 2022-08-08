package locations;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LocationsService {

    private List<Location> locations = Arrays.asList(
            new Location("Budapest", 47.497912, 19.040235),
            new Location("Róma", 41.90383, 12.50557),
            new Location("Athén", 37.97954, 23.72638)
    );

    public List<Location> getLocations() {
        return new ArrayList<>(locations);
    }
}
