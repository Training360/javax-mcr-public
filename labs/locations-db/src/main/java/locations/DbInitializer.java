//package locations;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DbInitializer implements CommandLineRunner {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void run(String... args) throws Exception {
//        jdbcTemplate.execute("create table locations (id bigint not null auto_increment, latitude double precision, longitude double precision, location_name varchar(255), primary key (id))");
//
//        jdbcTemplate.execute("insert into locations (latitude, longitude, location_name) values (47.497912, 19.040235, 'Budapest')");
//
//        jdbcTemplate.execute("insert into locations (latitude, longitude, location_name) values (41.90383, 12.50557, 'Róma')");
//
//        jdbcTemplate.execute("insert into locations (latitude, longitude, location_name) values (37.97954, 23.72638, 'Athén')");
//    }
//}
