package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestGenerationEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SourceStorage {

    private final List<Source> sources;

    private final EventHandler eventHandler;

    private final DispatcherInput dispatcherInput;

    @Value("${model.vars.requests}")
    private int requestAmount;

    private int requestId;

    public void generateRequestOnSource(int sourceId) {
        Request request = sources.get(sourceId).generateRequest(requestId++);
        eventHandler.addEvent(new RequestGenerationEvent(request, this, dispatcherInput));
    }

    public void start() {
        for (int i = 0; i < sources.size(); ++i) {
            generateRequestOnSource(i);
        }
    }

    public Source getSourceById(int id) {
        return sources.get(id);
    }
}
