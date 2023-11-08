package inc.stewie.queuingsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DispatcherInput {

    private final Buffer buffer;

    public void processRequest(Request request) {

    }

}
