package locations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
// Swagger UI
@Tag(name = "Web operations on locations")
public class LocationsController {

    //Bevezetés a Spring Boot használatába
//    private List<Location> locations = Arrays.asList(
//            new Location("Budapest", 47.497912, 19.040235),
//            new Location("Róma", 41.90383, 12.50557),
//            new Location("Athén", 37.97954, 23.72638)
//    );
//
//    @GetMapping("/locations")
//    public String getLocations() {
//        return locations.toString();
//    }

    // Spring Beanek
    private LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    // Spring Beanek
//    @GetMapping("/locations")
//    public String getLocations() {
//        return locationsService.getLocations().toString();
//    }

    // Developer Tools
//    @GetMapping("/locations")
//    public String getLocations() {
//        return locationsService.getLocations().toString() + " devtools";
//    }

    // REST webszolgáltatások - GET művelet
//    @GetMapping
//    public List<LocationDto> getLocations() {
//        return locationsService.getLocations();
//    }

    // GET műveletek paraméterezése
//    @GetMapping
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // Swagger UI
    @Operation(summary = "Gets all locations (optionally filtered) in a list")
    @ApiResponse(responseCode = "200", description = "Query of locations was successful")
//    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
//        return locationsService.getLocations(prefix);
//    }
    // Content Negotiation
    public LocationsDto getLocations(@RequestParam Optional<String> prefix) {
        return new LocationsDto(locationsService.getLocations(prefix));
    }

    // GET műveletek paraméterezése
//    @GetMapping("/{id}")
    // Content Negotiation
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // Swagger UI
    @Operation(summary = "Finds one exact location by its ID")
    @ApiResponse(responseCode = "200", description = "Location has been found")
    @ApiResponse(responseCode = "404", description = "Location has not been found")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationsService.findLocationById(id);
    }

    // REST webszolgáltatások POST és DELETE művelet - Create
//    @PostMapping
    // Content Negotiation
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    // Swagger UI
    @Operation(summary = "Creates a location")
    @ApiResponse(responseCode = "201", description = "Location has been created")
//    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
    // Validáció
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    // REST webszolgáltatások POST és DELETE művelet - Update
//    @PutMapping("/{id}")
    // Content Negotiation
    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    // Swagger UI
    @Operation(summary = "Changes the status of an exact location")
    @ApiResponse(responseCode = "200", description = "Changing of status was successful")
//    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
    // Validáció
    public LocationDto updateLocation(@PathVariable("id") long id, @Valid @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    // REST webszolgáltatások POST és DELETE művelet - Delete
//    @DeleteMapping("/{id}")
    // Content Negotiation
    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    // Swagger UI
    @Operation(summary = "Deletes one exact location")
    @ApiResponse(responseCode = "204", description = "Deletion of location was successful")
    public void deleteLocation(@PathVariable("id") long id) {
        locationsService.deleteLocation(id);
    }

    // Státuszkódok és hibakezelés
//    @ExceptionHandler(LocationNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Problem> handleNotFound(LocationNotFoundException lnfe) {
//        Problem problem =
//                Problem.builder()
//                        .withType(URI.create("locations/not-found"))
//                        .withTitle("Not found")
//                        .withStatus(Status.NOT_FOUND)
//                        .withDetail(lnfe.getMessage())
//                        .build();
//        return ResponseEntity
//                .status(HttpStatus.NOT_FOUND)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }

    // Validáció
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Problem> handleValidException(MethodArgumentNotValidException exception) {
//        List<Violation> violations =
//                exception.getBindingResult().getFieldErrors().stream()
//                        .map(fe -> new Violation(fe.getField(), fe.getDefaultMessage()))
//                        .collect(Collectors.toList());
//
//        Problem problem =
//                Problem.builder()
//                        .withType(URI.create("locations/not-valid"))
//                        .withTitle("Validation error")
//                        .withStatus(Status.BAD_REQUEST)
//                        .withDetail(exception.getMessage())
//                        .with("violations", violations)
//                        .build();
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
//                .body(problem);
//    }
}
