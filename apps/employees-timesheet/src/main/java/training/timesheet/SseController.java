package training.timesheet;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
@RequestMapping("/api/events/stream")
@Slf4j
public class SseController {

    private ModelMapper modelMapper;

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public SseEmitter getEvents() throws IOException {
        SseEmitter emitter = new SseEmitter(-1L);
        emitters.add(emitter);

        // Ha nem küldünk vissza választ, akkor timeoutol,
        // és nem próbál meg újra csatlakozni
        SseEmitter.SseEventBuilder builder = SseEmitter.event()
                .name("ack")
                .id(UUID.randomUUID().toString())
                .reconnectTime(5_000)
                .data("ok");
        emitter.send(builder);

        return emitter;
    }


    @EventListener
    public void employeeHasCreated(EmployeeHasCreatedEvent event) {
        log.debug("New event");
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                sendToEmitter(emitter, modelMapper.map(event, EmployeeDto.class));
            }
            catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        log.info("Removed emitters: " + deadEmitters.size());
        this.emitters.removeAll(deadEmitters);
    }

    private void sendToEmitter(SseEmitter emitter, EmployeeDto event) throws IOException {
        SseEmitter.SseEventBuilder builder = SseEmitter.event()
                .name("event")
                .id(UUID.randomUUID().toString())
                .reconnectTime(5_000)
                .data(event);
        emitter.send(builder);
    }

}
