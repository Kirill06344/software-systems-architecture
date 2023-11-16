package inc.stewie.queuingsystem;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Device {

    private final int alpha;

    private final int beta;

    @Getter
    private DeviceState state = DeviceState.FREE;

    private double calculateTimeOfProcessing() {
        return alpha + (beta - alpha) * Math.random();
    }

}
