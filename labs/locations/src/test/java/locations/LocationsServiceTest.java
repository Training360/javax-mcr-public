package locations;

import org.junit.jupiter.api.Test;
//import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

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
    void testGetLocations() {
//        LocationsService locationsService = new LocationsService(new ModelMapper());
        LocationsService locationsService = new LocationsService(new LocationMapperImpl());
        List<LocationDto> expected = locationsService.getLocations(Optional.of("B"));

        assertThat(expected)
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest");
    }
}