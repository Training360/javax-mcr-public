package locations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    private Long id;

    private String name;

    private double latitude;

    private double longitude;

    public Location(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Bevezetés a Spring Boot használatába
//    public Location(Long id, String name, double latitude, double longitude) {
//        this(name, latitude, longitude);
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public double getLatitude() {
//        return latitude;
//    }
//
//    public double getLongitude() {
//        return longitude;
//    }
//
//    @Override
//    public String toString() {
//        return "Location{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
