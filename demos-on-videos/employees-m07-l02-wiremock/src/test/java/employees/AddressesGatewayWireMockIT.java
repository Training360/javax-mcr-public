package employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.util.SocketUtils;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressesGatewayWireMockIT {

    static final String host = "localhost";

    static final int port = 8081;

    static WireMockServer wireMockServer;

    @BeforeAll
    static void startServer() {
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        WireMock.configureFor(host, port);
        wireMockServer.start();
    }

    @AfterAll
    static void stopServer() {
        wireMockServer.stop();
    }

    @BeforeEach
    void resetServer() {
        WireMock.reset();
    }

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

        AddressesGateway addressesGateway = new AddressesGateway(new RestTemplateBuilder());
        AddressDto addressDto = addressesGateway.findAddressByName("John Doe");

        verify(getRequestedFor(urlPathEqualTo(resource)).
                withQueryParam("name", equalTo("John Doe")));

        assertEquals("Budapest", addressDto.getCity());
    }
}
