package inc.stewie.queuingsystem;

import inc.stewie.queuingsystem.entity.BufferEntity;
import inc.stewie.queuingsystem.entity.SourceEntity;
import inc.stewie.queuingsystem.repository.BufferRepository;
import inc.stewie.queuingsystem.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

@Component
public class Buffer {

    private final SourceRepository sourceRepository;

    private final BufferRepository bufferRepository;

    private final Request [] requests;

    private final Queue<Integer> queue = new LinkedList<>();

    private int size;
    private int writePointer = 0;

    public Buffer(@Value("${model.vars.buffer}") int capacity, SourceRepository sourceRepository, BufferRepository bufferRepository) {
        requests = new Request[capacity];
        this.sourceRepository = sourceRepository;
        this.bufferRepository = bufferRepository;
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
        updateBufferPosition(inx + 1, request, time);
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
        removeRequestFromBuffer(readIndex + 1, time);
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
        updateRefuseAmount(refusedRequest, time);
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

    private void updateRefuseAmount(Request request, double time) {
        SourceEntity sourceEntity = sourceRepository.findById(request.sourceId())
                .orElse(new SourceEntity(request.sourceId()));
        sourceEntity.addRefuse(time);
        sourceRepository.save(sourceEntity);
    }

    private void updateBufferPosition(int position, Request request, double time) {
        BufferEntity bufferEntity = bufferRepository.findById(position).orElse(new BufferEntity(position));
        bufferEntity.updatePosition(request, time);
        bufferRepository.save(bufferEntity);
    }

    private void removeRequestFromBuffer(int position, double time) {
        Optional<BufferEntity> bufferEntity = bufferRepository.findById(position);
        if (bufferEntity.isEmpty()) {
            return;
        }
        bufferEntity.ifPresent(be -> be.pollRequest(time));
        bufferRepository.save(bufferEntity.get());
    }

}
