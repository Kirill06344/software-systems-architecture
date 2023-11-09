package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Request;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestToBufferEvent implements Event {

    private final Request request;

    private final int indexInBuffer;

    private final double time;

    @Override
    public void process() {

    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Request " + request.id() + " was placed in buffer by index " + indexInBuffer + " at " + time;
    }
}
