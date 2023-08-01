package employees;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AddressesGateway {

    private final RestTemplate restTemplate;

    public AddressesGateway(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    public AddressDto findAddressByName(String name) {
        return restTemplate.getForObject("http://localhost:8081/api/addresses?name={name}",
                AddressDto.class, name);
    }
}
