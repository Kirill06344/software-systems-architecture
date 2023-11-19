package inc.stewie.queuingsystem.devices;

import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.entity.DeviceEntity;
import inc.stewie.queuingsystem.events.EventHandler;
import inc.stewie.queuingsystem.events.RequestProcessingEvent;
import inc.stewie.queuingsystem.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceStorage {

    private final Map<Integer, Device> devices;

    private final EventHandler eventHandler;

    private final DeviceRepository repository;


    public boolean hasFreeDevice() {
        return devices.values().stream().anyMatch(device -> device.getState() == DeviceState.FREE);
    }

    /**
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
        eventHandler.addEvent(new RequestProcessingEvent(request, endTime, this, device.get().getId()));
        deliverRequestToDevice(device.get().getId(), time);
        return device.get().getId();
    }

    public void freeDevice(int id, double time) {
        devices.get(id).free();
        Optional<DeviceEntity> deviceEntity = repository.findById(id);
        if (deviceEntity.isEmpty()) {
            return;
        }

        deviceEntity.get().free(time);
        repository.save(deviceEntity.get());
    }

    private Optional<Device> getFreeDevice() {
        for (Device device : devices.values()) {
            if (device.getState() == DeviceState.FREE) {
                return Optional.of(device);
            }
        }
        return Optional.empty();
    }

    private void deliverRequestToDevice(int deviceId, double time) {
        DeviceEntity deviceEntity = repository.findById(deviceId).orElse(new DeviceEntity(deviceId));
        deviceEntity.processRequest(time);
        repository.save(deviceEntity);
    }


}
