package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.devices.Device;
import inc.stewie.queuingsystem.entity.BufferEntity;
import inc.stewie.queuingsystem.entity.SourceEntity;
import inc.stewie.queuingsystem.repository.BufferRepository;
import inc.stewie.queuingsystem.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RequestToBufferEvent implements Event {

    private final Request request;

    private final double time;

    private final Buffer buffer;

    @Override
    public void process() {
        if (buffer.isFilled()) {
            Request refusedRequest = buffer.refuseRequest(request, time);
            log.error("Request " + refusedRequest.id() + " was refused at " + time);
            Device.REFUSED_REQUESTS++;
            return;
        }

        int index = buffer.addRequest(request, time);
        log.info("Request " + request.id() + " was added into buffer on index " + index + " at " + time);
    }

    @Override
    public double getTime() {
        return time;
    }


}
