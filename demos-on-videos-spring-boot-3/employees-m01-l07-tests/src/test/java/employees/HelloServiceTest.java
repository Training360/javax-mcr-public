package employees;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloServiceTest {

    @Test
    void sayHello() {
        HelloService helloService = new HelloService();
        String message = helloService.sayHello();

        assertThat(message).startsWith("Hello");
    }
}