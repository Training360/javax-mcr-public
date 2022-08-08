package employees;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AddressesGateway {

    public AddressDto findAddressByName(String name) {
        return WebClient.create("http://localhost:8081")
                .get()
                .uri(builder -> builder.path("/api/addresses").queryParam("name", name).build())
                .retrieve()
                .bodyToMono(AddressDto.class)
                .block();
    }
}
