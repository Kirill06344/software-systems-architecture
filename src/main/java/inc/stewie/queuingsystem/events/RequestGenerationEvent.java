package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.DispatcherInput;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.SourceStorage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RequestGenerationEvent implements Event {

    private final Request request;

    private final SourceStorage sourceStorage;

    private final DispatcherInput dispatcherInput;

    @Override
    public void process() {
        sourceStorage.generateRequestOnSource(request.sourceId());
        dispatcherInput.processRequest(request);
    }

    @Override
    public Request getRequest() {
        return request;
    }

    @Override
    public double getTime() {
        return request.creationTime();
    }

    @Override
    public String toString() {
        return "Request " + request.id() + " generated on source " + request.sourceId() + " at " + request.creationTime();
    }
}
