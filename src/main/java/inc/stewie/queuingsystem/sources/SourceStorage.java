package inc.stewie.queuingsystem.sources;

import inc.stewie.queuingsystem.dispatchers.DispatcherInput;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestGenerationEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SourceStorage {

    private final Map<Integer, Source> sources;

    private final EventHandler eventHandler;

    private final DispatcherInput dispatcherInput;

    @Getter
    @Value("${model.vars.requests}")
    private int requestAmount;


    public void start() {
        for (var entry : sources.entrySet()) {
            Request request = entry.getValue().generateRequest(entry.getKey());
            generateEvent(request);
            requestAmount--;
        }
    }

    public void generateRequestOnSource(int sourceId) {
        if (requestAmount > 0) {
            Request request = sources.get(sourceId).generateRequest(sourceId);
            generateEvent(request);
            requestAmount--;
        }
    }

    private void generateEvent(Request request) {
        eventHandler.addEvent(new RequestGenerationEvent(request, this, dispatcherInput));
    }
}
