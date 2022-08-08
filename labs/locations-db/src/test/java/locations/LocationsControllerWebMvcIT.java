package locations;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocationsController.class)
class LocationsControllerWebMvcIT {

    @MockBean
    LocationsService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testGetLocations() throws Exception {
        when(service.getLocations(any())).thenReturn(List.of(new LocationDto(1L, "Róma", 41.90383, 12.50557),
                new LocationDto(2L, "Athén", 37.97954, 23.72638)));

        mockMvc.perform(get("/api/locations"))
                //            .andDo(print());
                .andExpect(status().isOk())
                .andExpect(jsonPath("locations[0].name", equalTo("Róma")))
                .andExpect(jsonPath("locations[1].name", equalTo("Athén")));
    }

    @Test
    void testFindLocationById() throws Exception {
        when(service.findLocationById(anyLong())).thenReturn(new LocationDto(2L, "Athén", 37.97954, 23.72638));

        mockMvc.perform(get("/api/locations/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Athén")));
    }

    @Test
    void testCreateLocation() throws Exception {
        when(service.createLocation(any())).thenReturn(new LocationDto(2L, "Athén", 37.97954, 23.72638));

        mockMvc.perform(post("/api/locations").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Moszkva\",\n" +
                        "  \"lat\": 55.76961,\n" +
                        "  \"lon\": 37.63722\n" +
                        "}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Athén")));
    }

    @Test
    void testCreateLocation2() throws Exception {
        when(service.createLocation(any())).thenReturn(new LocationDto(2L, "Athén", 37.97954, 23.72638));

        CreateLocationCommand command = new CreateLocationCommand("Moszkva", 55.76961, 37.63722);
        String json = objectMapper.writeValueAsString(command);
        mockMvc.perform(post("/api/locations").contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Athén")));
    }

    @Test
    void testUpdateLocation() throws Exception {
        when(service.updateLocation(anyLong(), any())).thenReturn(new LocationDto(2L, "Athén", 37.97954, 23.72638));

        mockMvc.perform(put("/api/locations/2").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"Moszkva\",\n" +
                        "  \"lat\": 55.76961,\n" +
                        "  \"lon\": 37.63722\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Athén")));
    }

    @Test
    void testUpdateLocation2() throws Exception {
        when(service.updateLocation(anyLong(), any())).thenReturn(new LocationDto(2L, "Athén", 37.97954, 23.72638));

        CreateLocationCommand command = new CreateLocationCommand("Moszkva", 55.76961, 37.63722);
        String json = objectMapper.writeValueAsString(command);
        mockMvc.perform(put("/api/locations/2").contentType(APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Athén")));
    }

    @Test
    void testCreateLocationWithNotValidNameLatitudeAndLongitude() throws Exception {
        mockMvc.perform(post("/api/locations").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"\",\n" +
                        "  \"lat\": -500,\n" +
                        "  \"lon\": 500\n" +
                        "}"))
                .andExpect(status().isBadRequest());

        verify(service, never()).createLocation(any());
    }

    @Test
    void testUpdateLocationWithNotValidNameLatitudeAndLongitude() throws Exception {
        mockMvc.perform(put("/api/locations/2").contentType(APPLICATION_JSON)
                .content("{\n" +
                        "  \"name\": \"\",\n" +
                        "  \"lat\": -500,\n" +
                        "  \"lon\": 500\n" +
                        "}"))
                .andExpect(status().isBadRequest());

        verify(service, never()).updateLocation(anyLong(), any());
    }
}
