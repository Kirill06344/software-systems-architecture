package inc.stewie.queuingsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Buffer {

    private final Request [] requests;

    private int size;
    private int writePointer = -1;
    private int readPointer = 0;

    public Buffer(@Value("${model.vars.buffer}") int capacity) {
        requests = new Request[capacity];
    }

    /**
     *
     * @param request - request we want to store in buffer
     * @return index in buffer where request was inserted
     * index = -1 if request was not inserted
     */
    public int addRequest(Request request) {
        if (isFilled()) {
            return -1;
        }

        writePointer++;
        requests[writePointer % requests.length] = request;
        return writePointer % requests.length;
    }

    public Request poll() {
        if (isEmpty()) {
            return null;
        }
        Request request = requests[readPointer % requests.length];
        readPointer++;
        return request;
    }

    public Request refuseRequest(Request request) {
        Request refusedRequest = requests[readPointer % requests.length];
        requests[readPointer % requests.length] = request;
        readPointer++;
        writePointer++;
        return refusedRequest;
    }

    public boolean isFilled() {
        return (writePointer - readPointer) + 1 == requests.length;
    }

    public boolean isEmpty() {
        return writePointer < readPointer;
    }

}
