package employees;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String sayHello() {
        return "Hello World " + LocalDateTime.now();
    }
}
