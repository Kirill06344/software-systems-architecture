package inc.stewie.queuingsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DispatcherOutput {

    private final DeviceStorage devices;
}
