package inc.stewie.queuingsystem.configuration;

import inc.stewie.queuingsystem.devices.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class DeviceConfiguration {

    @Value("${model.vars.devices}")
    private int devicesAmount;

    @Bean
    public Map<Integer,Device> devices() {
        Map<Integer, Device> devices = new HashMap<>();
        for (int i = 0 ; i < devicesAmount; ++i) {
            devices.put(i + 1, new Device(i + 1));
        }
        return devices;
    }
}
