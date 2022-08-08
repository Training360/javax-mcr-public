package locations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

    @Autowired
    LocationsService locationsService;

    // Unit és integrációs tesztek
//    @Test
//    void testGetLocations() {
//        String expected = locationsController.getLocations();
//
//        assertThat(expected).containsSubsequence("Budapest", "Róma", "Athén");
//    }

    // REST webszolgáltatások - GET művelet
//    @Test
//    void testGetLocations() {
//        List<LocationDto> expected = locationsController.getLocations();
//
//        assertThat(expected)
//                .hasSize(3)
//                .extracting(LocationDto::getName)
//                .containsExactly("Budapest", "Róma", "Athén");
//    }

    // GET műveletek paraméterezése
//    @Test
//    void testGetLocations() {
//        List<LocationDto> expected = locationsController.getLocations(Optional.of("B"));
//
//        assertThat(expected)
//                .hasSize(1)
//                .extracting(LocationDto::getName)
//                .containsExactly("Budapest");
//    }

    @BeforeEach
    void init() {
        locationsService.deleteAllLocations();

        locationsController.createLocation(new CreateLocationCommand("Budapest", 47.497912, 19.040235));
        locationsController.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));
        locationsController.createLocation(new CreateLocationCommand("Athén", 37.97954, 23.72638));
    }

    // Content Negotiation
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
        LocationDto expected = locationsController.findLocationById(2);

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        locationsController.updateLocation(2, new UpdateLocationCommand("Róma", 2.2, 3.3));

        LocationDto expected = locationsController.findLocationById(2);

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLatitude());
        assertEquals(3.3, expected.getLongitude());
    }

    @Test
    void testDeleteLocation() {
        locationsController.deleteLocation(2);

        LocationsDto expected = locationsController.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(2)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Athén");
    }
}