package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.devices.Device;
import inc.stewie.queuingsystem.devices.DeviceStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class RequestProcessingEvent implements Event {

    private final Request request;

    private final double time;

    private final DeviceStorage deviceStorage;

    private final int deviceId;

    @Override
    public void process() {
        log.info("Device " + deviceId + " processed request " + request.id() + " at " + time);
        deviceStorage.freeDevice(deviceId, time);
        Device.PROCESSED_REQUESTS++;
    }

    @Override
    public double getTime() {
        return time;
    }

}
