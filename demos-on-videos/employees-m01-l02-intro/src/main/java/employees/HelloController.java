package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello Spring Boot " + LocalDateTime.now();
    }
}
