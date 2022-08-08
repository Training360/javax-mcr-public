//package locations;
//
//import lombok.AllArgsConstructor;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.List;
//
//@Repository
//@AllArgsConstructor
//public class LocationsDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public List<Location> findAll() {
//        return jdbcTemplate.query("select * from locations",
//                this::mapRow);
//    }
//
//    public Location findById(long id) {
//        return jdbcTemplate.queryForObject("select * from locations where id = ?",
//                this::mapRow, id);
//    }
//
//    public Location save(Location location) {
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(connection -> {
//            PreparedStatement ps = connection.prepareStatement("insert into locations(latitude, longitude, location_name) values (?, ?, ?)",
//                    Statement.RETURN_GENERATED_KEYS);
//            ps.setDouble(1, location.getLatitude());
//            ps.setDouble(2, location.getLongitude());
//            ps.setString(3, location.getName());
//            return ps;
//        }, keyHolder);
//        location.setId(keyHolder.getKey().longValue());
//        return location;
//    }
//
//    private Location mapRow(ResultSet rs, int i) throws SQLException {
//        long id = rs.getLong("id");
//        String name = rs.getString("location_name");
//        double latitude = rs.getDouble("latitude");
//        double longitude = rs.getDouble("longitude");
//        Location location = new Location(id, name, latitude, longitude);
//        return location;
//    }
//
//    public Location updateLocation(Location location) {
//        jdbcTemplate.update("update locations set latitude = ?, longitude = ?, location_name = ? where id = ?",
//                location.getLatitude(), location.getLongitude(), location.getName(), location.getId());
//        return findById(location.getId());
//    }
//
//    public void deleteById(long id) {
//        jdbcTemplate.update("delete from locations where id = ?", id);
//    }
//
//    public void deleteAll() {
//        jdbcTemplate.update("delete from locations");
//    }
//}
