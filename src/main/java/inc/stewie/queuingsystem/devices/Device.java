package inc.stewie.queuingsystem.devices;

import inc.stewie.queuingsystem.ProbabilityDistributions;
import inc.stewie.queuingsystem.Request;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Device {

    public static int PROCESSED_REQUESTS = 0;

    public static int REFUSED_REQUESTS = 0;

    private final int id;

    private DeviceState state = DeviceState.FREE;

    public double processRequest(double time) {
        double endTime = time + ProbabilityDistributions.uniformDistribution();
        state = DeviceState.BUSY;
        return endTime;
    }

    public void free() {
        state = DeviceState.FREE;
    }




}
