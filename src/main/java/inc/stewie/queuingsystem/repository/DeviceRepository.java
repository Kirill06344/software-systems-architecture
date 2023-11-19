package inc.stewie.queuingsystem.repository;

import inc.stewie.queuingsystem.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
}
