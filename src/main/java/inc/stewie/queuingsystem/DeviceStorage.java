package inc.stewie.queuingsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeviceStorage {

    private final List<Device> devices;

    public Optional<Device> getDevice() {
        for (Device device : devices) {
            if (device.getState() == DeviceState.FREE) {
                return Optional.of(device);
            }
        }
        return Optional.empty();
    }

}
