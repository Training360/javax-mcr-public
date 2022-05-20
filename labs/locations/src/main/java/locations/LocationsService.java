package locations;

//import org.modelmapper.ModelMapper;
//import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class LocationsService {

    // REST webszolgáltatások - GET művelet
//    private ModelMapper modelMapper;

    // Spring Beanek
//    private List<Location> locations = Arrays.asList(
//            new Location(1L, "Budapest", 47.497912, 19.040235),
//            new Location(2L, "Róma", 41.90383, 12.50557),
//            new Location(3L, "Athén", 37.97954, 23.72638)
//    );

    // REST webszolgáltatások POST és DELETE művelet - Create
    private AtomicLong idGenerator = new AtomicLong();

    // REST webszolgáltatások POST és DELETE művelet - Create
    private List<Location> locations = new ArrayList<>(Arrays.asList(
            new Location(idGenerator.incrementAndGet(), "Budapest", 47.497912, 19.040235),
            new Location(idGenerator.incrementAndGet(), "Róma", 41.90383, 12.50557),
            new Location(idGenerator.incrementAndGet(), "Athén", 37.97954, 23.72638)
    ));

    // MapStruct
    private LocationMapper locationMapper;

    public LocationsService(LocationMapper locationMapper) {
        this.locationMapper = locationMapper;
    }

    //    public LocationsService(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    // Spring Beanek
//    public List<Location> getLocations() {
//        return new ArrayList<>(locations);
//    }

    // REST webszolgáltatások - GET művelet
//    public List<LocationDto> getLocations() {
//        Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();
//        return modelMapper.map(locations, targetListType);
//        return locationMapper.toDto(locations);
//    }

    // GET műveletek paraméterezése
    public List<LocationDto> getLocations(Optional<String> prefix) {
//        Type targetListType = new TypeToken<List<LocationDto>>() {}.getType();
        List<Location> filteredLocations = locations.stream()
                .filter(location -> prefix.isEmpty() || location.getName().startsWith(prefix.get()))
                .collect(Collectors.toList());
//        return modelMapper.map(filteredLocations, targetListType);
        return locationMapper.toDto(filteredLocations);
    }

    // GET műveletek paraméterezése
    public LocationDto findLocationById(long id) {
//        return modelMapper.map(
        return locationMapper.toDto(
                locations.stream()
                        .filter(location -> location.getId() == id)
                        .findAny()
//                        .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id))
                        .orElseThrow(() -> new LocationNotFoundException(id))
//                , LocationDto.class
        );
    }

    // REST webszolgáltatások POST és DELETE művelet - Create
    public LocationDto createLocation(CreateLocationCommand command) {
        Location location = new Location(idGenerator.incrementAndGet(),
                command.getName(), command.getLatitude(), command.getLongitude());
        locations.add(location);
//        return modelMapper.map(location, LocationDto.class);
        return locationMapper.toDto(location);
    }

    // REST webszolgáltatások POST és DELETE művelet - Update
    public LocationDto updateLocation(long id, UpdateLocationCommand command) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
//                .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id));
                .orElseThrow(() -> new LocationNotFoundException(id));
        location.setName(command.getName());
        location.setLatitude(command.getLatitude());
        location.setLongitude(command.getLongitude());
//        return modelMapper.map(location, LocationDto.class);
        return locationMapper.toDto(location);
    }

    // REST webszolgáltatások POST és DELETE művelet - Delete
    public void deleteLocation(long id) {
        Location location = locations.stream()
                .filter(l -> l.getId() == id)
                .findFirst()
//                .orElseThrow(() -> new LocationNotFoundException("Location not found: " + id));
                .orElseThrow(() -> new LocationNotFoundException(id));
        locations.remove(location);
    }
}
