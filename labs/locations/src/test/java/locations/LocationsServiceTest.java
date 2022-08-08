package locations;

import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationsServiceTest {

    // MapStruct
    LocationsService locationsService = new LocationsService(new LocationMapperImpl());

    // Unit és integrációs tesztek
//    @Test
//    void testGetLocations() {
//        LocationsService locationsService = new LocationsService();
//        List<Location> expected = locationsService.getLocations();
//
//        assertThat(expected)
//                .hasSize(3)
//                .extracting(Location::getName)
//                .containsExactly("Budapest", "Róma", "Athén");
//    }

    // REST webszolgáltatások - GET művelet
//    @Test
//    void testGetLocations() {
//        LocationsService locationsService = new LocationsService(new ModelMapper());
//        List<LocationDto> expected = locationsService.getLocations();
//
//        assertThat(expected)
//                .hasSize(3)
//                .extracting(LocationDto::getName)
//                .containsExactly("Budapest", "Róma", "Athén");
//    }

    // GET műveletek paraméterezése
    @Test
    void testGetLocationsByPrefix() {
//        LocationsService locationsService = new LocationsService(new ModelMapper());
        List<LocationDto> expected = locationsService.getLocations(Optional.of("B"));

        assertThat(expected)
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testGetLocations() {
        List<LocationDto> expected = locationsService.getLocations(Optional.empty());

        assertThat(expected)
                .hasSize(3)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Róma", "Athén");
    }

    @Test
    void testFindLocationById() {
        LocationDto expected = locationsService.findLocationById(2);

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        locationsService.updateLocation(2, new UpdateLocationCommand("Róma", 2.2, 3.3));

        LocationDto expected = locationsService.findLocationById(2);

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLatitude());
        assertEquals(3.3, expected.getLongitude());
    }

    @Test
    void testDeleteLocation() {
        locationsService.deleteLocation(2);

        List<LocationDto> expected = locationsService.getLocations(Optional.empty());

        assertThat(expected)
                .hasSize(2)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Athén");
    }
}