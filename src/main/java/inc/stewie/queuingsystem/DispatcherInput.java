package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestKnockingOutEvent;
import inc.stewie.queuingsystem.events.RequestToBufferEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DispatcherInput {

    private static final double PROCESSING_DELAY = 0.1;

    private final Buffer buffer;

    private final EventHandler eventHandler;

    public void processRequest(Request request) {
        double time = request.creationTime() + PROCESSING_DELAY;
        if (buffer.isFilled()) {
            Request refusedRequest = buffer.refuseRequest(request);
            eventHandler.addEvent(new RequestKnockingOutEvent(refusedRequest, request, time));
            return;
        }

        int index = buffer.addRequest(request);
        eventHandler.addEvent(new RequestToBufferEvent(request, index, time));
    }

}
