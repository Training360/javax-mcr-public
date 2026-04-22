package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmployeesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesApplication.class, args);
	}

//	@Bean
//	public HelloService helloService() {
//		return new HelloService();
//	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

}
