package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Integrációs tesztelés - MariaDB
@Sql(statements = "delete from locations")
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    LocationDto location;

    @BeforeEach
    void init() {
        locationsController.createLocation(new CreateLocationCommand("Budapest", 47.497912, 19.040235));
        location = locationsController.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));
        locationsController.createLocation(new CreateLocationCommand("Athén", 37.97954, 23.72638));
    }

    // Integrációs tesztelés - MariaDB
    @Test
    void testGetLocations() {
        LocationsDto expected = locationsController.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(3)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Róma", "Athén");
    }

    @Test
    void testGetLocationsByPrefix() {
        LocationsDto expected = locationsController.getLocations(Optional.of("B"));

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testFindLocationById() {
        LocationDto expected = locationsController.findLocationById(location.getId());

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        locationsController.updateLocation(location.getId(), new UpdateLocationCommand("Róma", 2.2, 3.3));

        LocationDto expected = locationsController.findLocationById(location.getId());

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLatitude());
        assertEquals(3.3, expected.getLongitude());
    }

    @Test
    void testDeleteLocation() {
        locationsController.deleteLocation(location.getId());

        LocationsDto expected = locationsController.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(2)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Athén");
    }
}