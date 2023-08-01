package employees;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EventStoreGateway {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(String name) {
        jmsTemplate.convertAndSend("eventsQueue", new CreateEventCommand("Employee has been created: " + name));

        jmsTemplate.convertAndSend("internalQueue", new CreateEventCommand("Employee has been created: " + name));
    }

    @JmsListener(destination = "internalQueue")
    public void processMessage(CreateEventCommand command) {
        log.info("Message has arrived: " + command.getMessage());
    }
}
