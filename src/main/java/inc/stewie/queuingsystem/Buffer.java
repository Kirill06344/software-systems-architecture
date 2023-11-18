package inc.stewie.queuingsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class Buffer {

    private final Request [] requests;

    private final Queue<Integer> queue = new LinkedList<>();

    private int size;
    private int writePointer = 0;

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
        queue.add(inx);
        writePointer = (inx + 1) % requests.length;
        return inx;
    }

    public Request poll() {
        if (queue.isEmpty()) {
            return null;
        }

        int readIndex = queue.poll();
        Request request = requests[readIndex];
        requests[readIndex] = null;
        size--;
        return request;
    }

    public Request refuseRequest(Request request) {
        if (queue.isEmpty()) {
            return null;
        }
        int readIndex = queue.poll();
        Request refusedRequest = requests[readIndex];
        requests[readIndex] = request;
        queue.add(readIndex);
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
