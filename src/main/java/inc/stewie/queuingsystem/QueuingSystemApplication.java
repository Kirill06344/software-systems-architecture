package inc.stewie.queuingsystem;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@AllArgsConstructor
public class QueuingSystemApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(QueuingSystemApplication.class, args);
        SourceStorage storage = context.getBean(SourceStorage.class);

        storage.start();
    }

}
