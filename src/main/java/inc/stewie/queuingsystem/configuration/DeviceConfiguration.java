package inc.stewie.queuingsystem.configuration;

import inc.stewie.queuingsystem.Device;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeviceConfiguration {

    @Value("${model.vars.alpha}")
    private int alpha;

    @Value("${model.vars.beta}")
    private int beta;

    @Value("${model.vars.devices}")
    private int devicesAmount;

    @Bean
    public List<Device> devices() {
        List<Device> devices = new ArrayList<>(devicesAmount);
        for (int i = 0 ; i < devicesAmount; ++i) {
            devices.add(new Device(alpha, beta));
        }
        return devices;
    }
}
