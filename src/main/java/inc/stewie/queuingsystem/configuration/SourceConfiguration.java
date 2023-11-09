package inc.stewie.queuingsystem.configuration;

import inc.stewie.queuingsystem.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SourceConfiguration {

    @Value("${model.vars.lambda}")
    private int lambda;

    @Value("${model.vars.sources}")
    private int sourcesAmount;

    @Bean
    public Map<Integer, Source> sources() {
        Map<Integer, Source> sources = new HashMap<>();
        for (int i = 0; i < sourcesAmount; ++i) {
            sources.put(i, new Source(lambda));
        }
        return sources;
    }
}
