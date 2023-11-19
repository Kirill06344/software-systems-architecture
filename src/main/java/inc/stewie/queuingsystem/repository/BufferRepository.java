package inc.stewie.queuingsystem.repository;

import ch.qos.logback.core.model.INamedModel;
import inc.stewie.queuingsystem.entity.BufferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BufferRepository extends JpaRepository<BufferEntity, Integer> {

    Optional<BufferEntity> findBufferEntityByRequestNumber(int requestNumber);
}
