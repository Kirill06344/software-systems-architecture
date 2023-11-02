package inc.stewie.queuingsystem.configuration;

import inc.stewie.queuingsystem.Source;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SourceConfiguration {

    @Value("${model.vars.lambda}")
    private int lambda;

    @Value("${model.vars.sources}")
    private int sourcesAmount;

    @Bean
    public List<Source> sources() {
        List<Source> sources = new ArrayList<>(sourcesAmount);
        for (int i = 0; i < sourcesAmount; ++i) {
            sources.add(new Source(lambda));
        }
        return sources;
    }
}
