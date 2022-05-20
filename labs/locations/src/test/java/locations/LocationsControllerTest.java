package locations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    // Unit és integrációs tesztek
//    @Test
//    void testGetLocations() {
//        List<Location> locations = Arrays.asList(
//                new Location("Sydney", -33.88223, 151.33140)
//        );
//        when(locationsService.getLocations()).thenReturn(locations);
//        String expected = locationsController.getLocations();
//
//        assertThat(expected).contains("Sydney");
//    }

    // REST webszolgáltatások - GET művelet
//    @Test
//    void testGetLocations() {
//        List<LocationDto> locations = Arrays.asList(
//                new LocationDto("Sydney", -33.88223, 151.33140)
//        );
//        when(locationsService.getLocations()).thenReturn(locations);
//        List<LocationDto> expected = locationsController.getLocations();
//
//        assertThat(expected)
//                .hasSize(1)
//                .extracting(LocationDto::getName)
//                .containsOnly("Sydney");
//    }

    // GET műveletek paraméterezése
    @Test
    void testGetLocations() {
        List<LocationDto> locations = Arrays.asList(
                new LocationDto("Sydney", -33.88223, 151.33140)
        );
        when(locationsService.getLocations(any())).thenReturn(locations);
        List<LocationDto> expected = locationsController.getLocations(Optional.empty());

        assertThat(expected)
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsOnly("Sydney");
    }
}