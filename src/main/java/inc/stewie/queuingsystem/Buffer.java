package inc.stewie.queuingsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Buffer {

    private final Request [] requests;
    private int size;

    private int lastIndex = -1;

    public Buffer(@Value("${model.vars.requests") int capacity) {
        requests = new Request[capacity];
    }

    public void addRequest(Request request) {
        
    }

    public boolean isFilled() {
        return size == requests.length;
    }

}
