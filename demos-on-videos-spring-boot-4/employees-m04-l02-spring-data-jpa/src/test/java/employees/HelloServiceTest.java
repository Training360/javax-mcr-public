package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void sayHello() {
        HelloProperties helloProperties = new HelloProperties();
        helloProperties.setMessage("Hello");
        HelloService helloService = new HelloService(helloProperties);
        String message = helloService.sayHello();

        assertThat(message).startsWith("Hello");
    }
}