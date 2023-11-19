package inc.stewie.queuingsystem.dispatchers;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.entity.SourceEntity;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestToBufferEvent;
import inc.stewie.queuingsystem.repository.BufferRepository;
import inc.stewie.queuingsystem.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DispatcherInput {

    private final Buffer buffer;

    private final EventHandler eventHandler;

    private final SourceRepository sourceRepository;

    private final BufferRepository bufferRepository;

    public void processRequest(Request request) {
        updateRequestAmount(request);
        double time = request.creationTime() + imitateDelay();
        eventHandler.addEvent(new RequestToBufferEvent(request, time, buffer));
    }

    private double imitateDelay() {
        return Math.random();
    }

    private void updateRequestAmount(Request request) {
        SourceEntity sourceEntity = sourceRepository.findById(request.sourceId()).orElse(new SourceEntity(request.sourceId()));
        sourceEntity.addRequest(request.creationTime());
        sourceRepository.save(sourceEntity);
    }

}
