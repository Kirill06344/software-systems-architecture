package inc.stewie.queuingsystem;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
@AllArgsConstructor
public class QueuingSystemApplication {

    public static void main(String[] args) throws IOException {
        final ConfigurableApplicationContext context = SpringApplication.run(QueuingSystemApplication.class, args);
        Simulation simulation = context.getBean(Simulation.class);

        simulation.start();
    }

}
