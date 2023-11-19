package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.dispatchers.DispatcherInput;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.entity.SourceEntity;
import inc.stewie.queuingsystem.repository.SourceRepository;
import inc.stewie.queuingsystem.sources.SourceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RequestGenerationEvent implements Event {

    private final Request request;

    private final SourceStorage sourceStorage;

    private final DispatcherInput dispatcherInput;

    @Override
    public void process() {
        sourceStorage.generateRequestOnSource(request.sourceId());
        log.info("Request " + request.id() + " generated on source " + request.sourceId() + " at " + request.creationTime());
        dispatcherInput.processRequest(request);
    }

    @Override
    public double getTime() {
        return request.creationTime();
    }

}
