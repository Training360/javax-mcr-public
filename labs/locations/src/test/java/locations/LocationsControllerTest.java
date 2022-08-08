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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
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
//    @Test
//    void testGetLocations() {
//        List<LocationDto> locations = Arrays.asList(
//                new LocationDto("Sydney", -33.88223, 151.33140)
//        );
//        when(locationsService.getLocations(any())).thenReturn(locations);
//        List<LocationDto> expected = locationsController.getLocations(Optional.empty());
//
//        assertThat(expected)
//                .hasSize(1)
//                .extracting(LocationDto::getName)
//                .containsOnly("Sydney");
//    }

    // Content Negotiation
    @Test
    void testGetLocations() {
        List<LocationDto> locations = Arrays.asList(
                new LocationDto("Sydney", -33.88223, 151.33140)
        );
        when(locationsService.getLocations(any())).thenReturn(locations);
        LocationsDto expected = locationsController.getLocations(Optional.empty());

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsOnly("Sydney");
    }

    @Test
    void testGetLocationsByPrefix() {
        List<LocationDto> locations = Arrays.asList(
                new LocationDto("Budapest", 47.497912, 19.040235)
        );
        when(locationsService.getLocations(any())).thenReturn(locations);
        LocationsDto expected = locationsController.getLocations(Optional.of("B"));

        assertThat(expected.getLocations())
                .hasSize(1)
                .extracting(LocationDto::getName)
                .containsExactly("Budapest");
    }

    @Test
    void testFindLocationById() {
        when(locationsService.findLocationById(anyLong())).thenReturn(new LocationDto("Róma", 2.2, 3.3));
        LocationDto expected = locationsController.findLocationById(2);

        assertEquals("Róma", expected.getName());
    }

    @Test
    void testUpdateLocation() {
        when(locationsService.updateLocation(anyLong(), any())).thenReturn(new LocationDto("Róma", 2.2, 3.3));
        LocationDto expected = locationsController.updateLocation(2, new UpdateLocationCommand("Róma", 1.1, 1.1));

        assertEquals("Róma", expected.getName());
        assertEquals(2.2, expected.getLatitude());
        assertEquals(3.3, expected.getLongitude());
    }

    @Test
    void testDeleteLocation() {
        locationsController.deleteLocation(2);
        verify(locationsService).deleteLocation(2);
    }
}