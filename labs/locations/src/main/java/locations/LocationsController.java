package locations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
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
    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<String> prefix) {
        return locationsService.getLocations(prefix);
    }

    // GET műveletek paraméterezése
    @GetMapping("/{id}")
    public LocationDto findLocationById(@PathVariable("id") long id) {
        return locationsService.findLocationById(id);
    }

    // REST webszolgáltatások POST és DELETE művelet - Create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody CreateLocationCommand command) {
        return locationsService.createLocation(command);
    }

    // REST webszolgáltatások POST és DELETE művelet - Update
    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable("id") long id, @RequestBody UpdateLocationCommand command) {
        return locationsService.updateLocation(id, command);
    }

    // REST webszolgáltatások POST és DELETE művelet - Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
}
