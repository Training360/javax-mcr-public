package locations;

//import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LocationsSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocationsSpringBootApplication.class, args);
	}

	// Konfiguráció Javaban
//	@Bean
//	public LocationsService locationsService() {
//		return new LocationsService();
//	}

	// REST webszolgáltatások - GET művelet
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

	// Státuszkódok és hibakezelés
//	@Bean
//	public ObjectMapper objectMapper() {
//		return new ObjectMapper().findAndRegisterModules();
//	}
}
