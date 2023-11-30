package inc.stewie.queuingsystem.service;

import inc.stewie.queuingsystem.Request;
import inc.stewie.queuingsystem.entity.BufferEntity;
import inc.stewie.queuingsystem.entity.DeviceEntity;
import inc.stewie.queuingsystem.entity.SourceEntity;
import inc.stewie.queuingsystem.repository.BufferRepository;
import inc.stewie.queuingsystem.repository.DeviceRepository;
import inc.stewie.queuingsystem.repository.SourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final SourceRepository sourceRepository;

    private final BufferRepository bufferRepository;

    private final DeviceRepository deviceRepository;

    public void updateRequestAmount(Request request) {
        SourceEntity sourceEntity = sourceRepository.findById(request.sourceId()).orElse(new SourceEntity(request.sourceId()));
        sourceEntity.addRequest(request.creationTime());
        sourceRepository.save(sourceEntity);
    }

    public void updateRefuseAmount(Request request, double time) {
        SourceEntity sourceEntity = sourceRepository.findById(request.sourceId())
                .orElse(new SourceEntity(request.sourceId()));
        sourceEntity.addRefuse(time);
        sourceRepository.save(sourceEntity);
    }

    public void updateBufferPosition(int position, Request request, double time) {
        BufferEntity bufferEntity = bufferRepository.findById(position).orElse(new BufferEntity(position + 1));
        bufferEntity.updatePosition(request, time);
        bufferRepository.save(bufferEntity);
    }

    public void removeRequestFromBuffer(int position, double time) {
        Optional<BufferEntity> bufferEntity = bufferRepository.findById(position + 1);
        if (bufferEntity.isEmpty()) {
            return;
        }
        bufferEntity.ifPresent(be -> be.pollRequest(time));
        bufferRepository.save(bufferEntity.get());
    }

    public void deliverRequestToDevice(int deviceId, double time) {
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId).orElse(new DeviceEntity(deviceId));
        deviceEntity.processRequest(time);
        deviceRepository.save(deviceEntity);
    }

    public void freeDevice(int id, double time) {
        Optional<DeviceEntity> deviceEntity = deviceRepository.findById(id);
        if (deviceEntity.isEmpty()) {
            return;
        }

        deviceEntity.get().free(time);
        deviceRepository.save(deviceEntity.get());
    }
}
