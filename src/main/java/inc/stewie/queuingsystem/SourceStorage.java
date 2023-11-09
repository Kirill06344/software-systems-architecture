package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestGenerationEvent;
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

    @Value("${model.vars.requests}")
    private int requestAmount;

    private int requestId;


    public void start() {
        for (Integer key : sources.keySet()) {
            generateRequestOnSource(key);
        }
    }

    public void generateRequestOnSource(int sourceId) {
        Request request = sources.get(sourceId).generateRequest(requestId++, sourceId);
        generateEvent(request);
    }

    public Source getSourceById(int id) {
        return sources.get(id);
    }

    private void generateEvent(Request request) {
        eventHandler.addEvent(new RequestGenerationEvent(request, this, dispatcherInput));
    }
}
