package inc.stewie.queuingsystem.configuration;

import inc.stewie.queuingsystem.sources.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SourceConfiguration {

    @Value("${model.vars.sources}")
    private int sourcesAmount;

    @Bean
    @DependsOn("probabilityDistributions")
    public Map<Integer, Source> sources() {
        Map<Integer, Source> sources = new HashMap<>();
        for (int i = 0; i < sourcesAmount; ++i) {
            sources.put(i + 1, new Source());
        }
        return sources;
    }
}
