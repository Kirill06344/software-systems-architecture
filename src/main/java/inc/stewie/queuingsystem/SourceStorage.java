package inc.stewie.queuingsystem;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SourceStorage {

    private final List<Source> sources;

    @Value("${model.vars.requests}")
    private int requestAmount;

    public void start() {
        for (Source source : sources) {
            System.out.println(source.getId() + " " + source.generateRequestTime());
        }
    }
}
