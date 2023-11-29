package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
//import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
//import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
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
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper().findAndRegisterModules();
	}

//	@Bean
//	public HttpTraceRepository httpTraceRepository() {
//		return new InMemoryHttpTraceRepository();
//	}

	@Bean
	public AuditEventRepository auditEventRepository() {
		return new InMemoryAuditEventRepository();
	}
}
