package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.events.Event;
import inc.stewie.queuingsystem.events.EventHandler;
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
        final SourceStorage storage = context.getBean(SourceStorage.class);

        storage.start();

        EventHandler events = context.getBean(EventHandler.class);
        while (!events.isEmpty() && System.in.read() != -1) {
            Event event = events.getNextEvent();
            event.process();
            System.out.println(event);
        }
    }

}
