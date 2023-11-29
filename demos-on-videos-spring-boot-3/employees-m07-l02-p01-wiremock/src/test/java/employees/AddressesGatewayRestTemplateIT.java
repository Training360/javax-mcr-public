package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.queryParam;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(value = AddressesGateway.class)
public class AddressesGatewayRestTemplateIT {

    @Autowired
    AddressesGateway addressesGateway;

    @Autowired
    MockRestServiceServer serviceServer;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testFindAddress() throws Exception {
        String json = objectMapper.writeValueAsString(new AddressDto("Budapest", "Fő út"));

        serviceServer.expect(requestTo(startsWith("http://localhost:8081/api/addresses")))
                .andExpect(queryParam("name", "John%20Doe"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        AddressDto addressDto = addressesGateway.findAddressByName("John Doe");

        assertEquals("Budapest", addressDto.getCity());
    }
}
