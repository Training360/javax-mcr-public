package locations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsControllerIT {

    @Autowired
    LocationsController locationsController;

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
    @Test
    void testGetLocations() {
        List<LocationDto> expected = locationsController.getLocations(Optional.of("B"));

        assertThat(expected)
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest");
    }
}