package training.addresses;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;

@Service
public class AddressesService {

    private List<Address> addresses;

    private ObjectMapper objectMapper;

    private ModelMapper modelMapper;

    private ApplicationEventPublisher publisher;

    private Random random = new Random();

    public AddressesService(ObjectMapper objectMapper, ModelMapper modelMapper, ApplicationEventPublisher publisher) {
        this.objectMapper = objectMapper;
        this.modelMapper = modelMapper;
        this.publisher = publisher;
    }

    @PostConstruct
    public void loadAddresses() throws IOException {
        InputStream s = AddressesService.class.getResourceAsStream("/addresses.json");
        try (s) {
            addresses = objectMapper.readValue(s, new TypeReference<List<Address>>(){});
        }
    }

    public AddressDto getAddressToEmployee(String name) {
        publisher.publishEvent(new AddressHasRequestedEvent(name));
        return modelMapper.map(addresses.get(random.nextInt(addresses.size())), AddressDto.class);
    }
}
