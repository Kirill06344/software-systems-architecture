package inc.stewie.queuingsystem.dispatchers;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestToBufferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DispatcherInput {

    private final Buffer buffer;

    private final EventHandler eventHandler;

    public void processRequest(Request request) {
        double time = request.creationTime() + imitateDelay();
        eventHandler.addEvent(new RequestToBufferEvent(request, time, buffer));
    }

    private double imitateDelay() {
        return Math.random();
    }

}
