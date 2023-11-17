package inc.stewie.queuingsystem.dispatchers;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.devices.DeviceStorage;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestFromBufferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DispatcherOutput {

    private final Buffer buffer;

    private final DeviceStorage deviceStorage;

    private final EventHandler eventHandler;

    public void createEvent() {
        double time = eventHandler.getCurrentTime() + imitateDelay();
        eventHandler.addEvent(new RequestFromBufferEvent(time, buffer, deviceStorage));
    }

    private double imitateDelay() {
        return Math.random();
    }

}
