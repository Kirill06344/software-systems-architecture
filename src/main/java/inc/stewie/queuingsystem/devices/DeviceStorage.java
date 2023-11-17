package inc.stewie.queuingsystem.devices;

import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestProcessingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceStorage {

    private final List<Device> devices;

    private final EventHandler eventHandler;


    public boolean hasFreeDevice() {
        return devices.stream().anyMatch(device -> device.getState() == DeviceState.FREE);
    }

    /**
     *
     * @param request
     * @param time
     * @return index of processing device
     */
    public int processRequest(Request request, double time) {
        Optional<Device> device = getFreeDevice();
        if (device.isEmpty()) {
            return -1;
        }

        double endTime = device.get().processRequest(time);
        eventHandler.addEvent(new RequestProcessingEvent(request, endTime, device.get()));
        return device.get().getId();
    }

    private Optional<Device> getFreeDevice() {
        for (Device device : devices) {
            if (device.getState() == DeviceState.FREE) {
                return Optional.of(device);
            }
        }
        return Optional.empty();
    }




}
