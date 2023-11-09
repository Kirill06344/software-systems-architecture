package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Request;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestKnockingOutEvent implements Event{

    private final Request refusedRequest;

    private final Request request;

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
        return "Request " + refusedRequest.id() + " was refused at " + time +
                "\nIt's place was taken by request " + request.id();
    }
}
