package training.addresses;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final AddressesService addressesService;

    @GetMapping
    public AddressDto getAddressToEmployees(@Parameter(description = "name of the employee", example = "John Doe") @RequestParam String name) {
        return addressesService.getAddressToEmployee(name);
    }
}
