package locations;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

// Státuszkódok és hibakezelés
//public class LocationNotFoundException extends RuntimeException {

// Státuszkódok és hibakezelés problem-spring-web-starterrel
public class LocationNotFoundException extends AbstractThrowableProblem {

//    public LocationNotFoundException() {
//    }
//
//    public LocationNotFoundException(String message) {
//        super(message);
//    }
//
//    public LocationNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }

    public LocationNotFoundException(long id) {
        super(URI.create("locations/location-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Location with id %d not found", id));
    }
}
