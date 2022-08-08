package locations;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
// Integrációs tesztelés - MariaDB
@Sql(statements = "delete from locations")
class LocationsControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationsService service;

    LocationDto location;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification =
                given()
                        .contentType(ContentType.JSON)
                        .accept(ContentType.JSON);

//        service.deleteAllLocations();

        with()
                .body(new CreateLocationCommand("Budapest", 47.497912, 19.040235))
                .post("/api/locations")
                .then()
                .statusCode(201);

        location = service.createLocation(new CreateLocationCommand("Róma", 41.90383, 12.50557));

        with()
                .body(new CreateLocationCommand("Athén", 37.97954, 23.72638))
                .post("/api/locations")
                .then()
                .statusCode(201);
    }

    @Test
    void testGetLocations() {
        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("locations.size()", equalTo(3))
                .body("locations[0].name", equalTo("Budapest"))
                .body("locations[1].name", equalTo("Róma"))
                .body("locations[2].name", equalTo("Athén"));
    }

    @Test
    void testFindLocationById() {
        with()
                .get("/api/locations/" + location.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Róma"))
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }

    @Test
    void testGetLocationsByName() {
        with()
                .body(new CreateLocationCommand("Bécs", 48.2083, 16.3731))
                .post("/api/locations")
                .then()
                .statusCode(201);

        with()
                .get("/api/locations?prefix=B")
                .then()
                .statusCode(200)
                .body("locations.size()", equalTo(2))
                .body("locations[0].name", equalTo("Budapest"))
                .body("locations[1].name", equalTo("Bécs"))
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }

    @Test
    void testUpdateLocation() {
        with()
                .body(new UpdateLocationCommand("Unknown", 2.2, 3.3))
                .put("/api/locations/" + location.getId())
                .then()
                .statusCode(200)
                .body("name", equalTo("Unknown"))
                .body("latitude", equalTo(2.2F))
                .body("longitude", equalTo(3.3F))
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }

    @Test
    void testDeleteLocation() {
        with()
                .delete("/api/locations/" + location.getId())
                .then()
                .statusCode(204);

        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("locations.size()", equalTo(2))
                .body("locations[0].name", equalTo("Budapest"))
                .body("locations[1].name", equalTo("Athén"));
    }

    @Test
    void testCreateLocationWithNotValidNameLatitudeAndLongitude() {
        with()
                .body(new CreateLocationCommand("", -500, 500))
                .post("/api/locations")
                .then()
                .statusCode(400)
                .body("title", equalTo("Constraint Violation"));
    }

    @Test
    void testUpdateLocationWithNotValidNameLatitudeAndLongitude() {
        with()
                .body(new UpdateLocationCommand("", -500, 500))
                .put("/api/locations/" + location.getId())
                .then()
                .statusCode(400)
                .body("title", equalTo("Constraint Violation"));
    }
}
