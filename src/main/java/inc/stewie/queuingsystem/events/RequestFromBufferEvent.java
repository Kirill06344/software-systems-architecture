package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Buffer;
import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.devices.DeviceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
