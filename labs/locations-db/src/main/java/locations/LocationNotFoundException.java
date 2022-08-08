package locations;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class LocationNotFoundException extends AbstractThrowableProblem {

    public LocationNotFoundException(long id) {
        super(URI.create("locations/location-not-found"),
                "Not found",
                Status.NOT_FOUND,
                String.format("Location with id %d not found", id));
    }
}
