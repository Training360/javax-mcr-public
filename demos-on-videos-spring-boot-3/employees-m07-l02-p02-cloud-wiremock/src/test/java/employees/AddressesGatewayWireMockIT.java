package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWireMock(port = 8081)
class AddressesGatewayWireMockIT {

    @Autowired
    AddressesGateway addressesGateway;

    @Test
    void testFindAddress() throws Exception {
        String resource = "/api/addresses";

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(new AddressDto("Budapest", "Fő út"));

        stubFor(get(urlPathEqualTo(resource))
                .willReturn(aResponse()
                    .withHeader("Content-Type", "application/json")
                    .withBody(json)
                ));

        AddressDto addressDto = addressesGateway.findAddressByName("John Doe");

        verify(getRequestedFor(urlPathEqualTo(resource)).
                withQueryParam("name", equalTo("John Doe")));

        assertEquals("Budapest", addressDto.getCity());
    }
}
