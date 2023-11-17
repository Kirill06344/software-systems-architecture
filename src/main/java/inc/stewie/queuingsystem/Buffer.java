package inc.stewie.queuingsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Buffer {

    private final Request [] requests;

    private int size;
    private int writePointer = 0;

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

        int inx = getFreePlace(writePointer);
        requests[inx] = request;
        size++;
        writePointer = (inx + 1) % requests.length;
        return inx;
    }

    public Request poll() {
        if (isEmpty()) {
            return null;
        }

        Request request = requests[readPointer];
        requests[readPointer] = null;
        readPointer = (readPointer + 1) % requests.length;
        size--;
        return request;
    }

    public Request refuseRequest(Request request) {
        Request refusedRequest = requests[readPointer];
        requests[readPointer] = request;
        readPointer = (readPointer + 1) % requests.length;
        return refusedRequest;
    }

    private int getFreePlace(int startIndex) {
        int i = startIndex;
        while (requests[i] != null) {
            i = (i + 1) % requests.length;
        }
        return i;
    }

    public boolean isFilled() {
        return size == requests.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
