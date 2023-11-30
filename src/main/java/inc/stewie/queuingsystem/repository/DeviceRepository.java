package inc.stewie.queuingsystem.repository;

import inc.stewie.queuingsystem.entity.DeviceEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE devices", nativeQuery = true)
    void truncateTable();
}
