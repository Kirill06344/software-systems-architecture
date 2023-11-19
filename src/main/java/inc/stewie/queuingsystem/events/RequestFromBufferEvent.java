package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.devices.DeviceStorage;
import inc.stewie.queuingsystem.entity.BufferEntity;
import inc.stewie.queuingsystem.entity.DeviceEntity;
import inc.stewie.queuingsystem.repository.BufferRepository;
import inc.stewie.queuingsystem.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


@RequiredArgsConstructor
@Slf4j
public class RequestFromBufferEvent implements Event {

    private final double time;

    private final Buffer buffer;

    private final DeviceStorage deviceStorage;

    @Override
    public void process() {
        if (!deviceStorage.hasFreeDevice() || buffer.isEmpty()) {
            return;
        }

        Request request = buffer.poll(time);
        int deviceId = deviceStorage.processRequest(request, time);
        log.info("Device " + deviceId + " took for processing request " + request.id() + " at " + time);
    }

    @Override
    public double getTime() {
        return time;
    }





}
