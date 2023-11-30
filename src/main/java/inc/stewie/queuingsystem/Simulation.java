package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.devices.Device;
import inc.stewie.queuingsystem.dispatchers.DispatcherOutput;
import inc.stewie.queuingsystem.events.Event;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.service.StatisticsService;
import inc.stewie.queuingsystem.sources.SourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class Simulation {

    @Value("${model.vars.requests}")
    private int requestAmount;

    private final SourceStorage sourceStorage;

    private final EventHandler events;

    private final DispatcherOutput dispatcherOutput;

    public void start(){
        sourceStorage.start();

        while (!events.isEmpty() && Device.PROCESSED_REQUESTS + Device.REFUSED_REQUESTS < requestAmount) {
            Event event = events.getNextEvent();
            event.process();
            dispatcherOutput.createEvent();
        }

        log.info("Refused requests: " + Device.REFUSED_REQUESTS);
        log.info("Processed requests: " + Device.PROCESSED_REQUESTS);
    }

}
