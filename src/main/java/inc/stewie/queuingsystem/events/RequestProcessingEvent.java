package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.devices.Device;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RequestProcessingEvent implements Event {

    private final Request request;

    private final double time;

    private final Device device;

    @Override
    public void process() {
        log.info("Device " + device.getId() + " processed request " + request.id() + " at " + time);
        device.freeDevice();
        Device.PROCESSED_REQUESTS++;
    }

    @Override
    public double getTime() {
        return time;
    }

}
