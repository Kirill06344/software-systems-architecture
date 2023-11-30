package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.service.StatisticsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class Buffer {

    private final StatisticsService statisticsService;

    private final Request [] requests;

    private final Queue<Integer> queue = new LinkedList<>();

    private int size;
    private int writePointer = 0;

    public Buffer(@Value("${model.vars.buffer}") int capacity, StatisticsService statisticsService) {
        requests = new Request[capacity];
        this.statisticsService = statisticsService;
    }

    /**
     *
     * @param request - request we want to store in buffer
     * @return index in buffer where request was inserted
     * index = -1 if request was not inserted
     */
    public int addRequest(Request request, double time) {
        if (isFilled()) {
            return -1;
        }

        int inx = getFreePlace(writePointer);
        requests[inx] = request;
        statisticsService.updateBufferPosition(inx, request, time);
        size++;
        queue.add(inx);
        writePointer = (inx + 1) % requests.length;
        return inx;
    }

    public Request poll(double time) {
        if (queue.isEmpty()) {
            return null;
        }

        int readIndex = queue.poll();
        Request request = requests[readIndex];
        requests[readIndex] = null;
        statisticsService.removeRequestFromBuffer(readIndex, time);
        size--;
        return request;
    }

    public Request refuseRequest(Request request, double time) {
        if (queue.isEmpty()) {
            return null;
        }
        int readIndex = queue.poll();
        Request refusedRequest = requests[readIndex];
        requests[readIndex] = request;
        queue.add(readIndex);
        statisticsService.updateRefuseAmount(refusedRequest, time);
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
