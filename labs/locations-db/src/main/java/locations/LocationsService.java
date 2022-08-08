package locations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
// Spring JdbcTemplate
@AllArgsConstructor
public class LocationsService {

    // Spring JdbcTemplate
//    private LocationsDao repository;

    // Spring Data JPA
    private LocationsRepository repository;

    private LocationMapper locationMapper;

    // Spring JdbcTemplate
    public List<LocationDto> getLocations(Optional<String> prefix) {
        return locationMapper.toDto(
                repository.findAll().stream()
                        .filter(location -> prefix.isEmpty() || location.getName().startsWith(prefix.get()))
                        .collect(Collectors.toList())
        );
    }

    // Spring JdbcTemplate
    public LocationDto findLocationById(long id) {
        return locationMapper.toDto(
                repository.findById(id)
                        // Spring Data JPA
                        .orElseThrow(() -> new LocationNotFoundException(id))
        );
    }

    // Spring JdbcTemplate
    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(
                command.getName(), command.getLatitude(), command.getLongitude());
        repository.save(location);
        log.info("Location with id = " + location.getId() + " has been created.");
        return locationMapper.toDto(location);
    }

    // Spring JdbcTemplate
//    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
//        Location location = new Location(id, command.getName(), command.getLatitude(), command.getLongitude());
//        repository.updateLocation(location);
//        log.info("Location with id = " + id + " has been updated.");
//        return locationMapper.toDto(location);
//    }

    // Spring Data JPA
    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = repository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
        location.setName(command.getName());
        location.setLatitude(command.getLatitude());
        location.setLongitude(command.getLongitude());
        log.info("Location with id = " + id + " has been updated.");
        return locationMapper.toDto(location);
    }

    // Spring JdbcTemplate
    public void deleteLocation(long id) {
        repository.deleteById(id);
        log.info("Location with id = " + id + " has been deleted.");
    }

    // Spring JdbcTemplate
    public void deleteAllLocations() {
        repository.deleteAll();
    }
}
