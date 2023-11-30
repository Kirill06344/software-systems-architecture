package inc.stewie.queuingsystem.repository;

import inc.stewie.queuingsystem.entity.BufferEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface BufferRepository extends JpaRepository<BufferEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE buffer", nativeQuery = true)
    void truncateTable();

}
