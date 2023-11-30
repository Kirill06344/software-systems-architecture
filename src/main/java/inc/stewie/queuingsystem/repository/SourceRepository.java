package inc.stewie.queuingsystem.repository;

import inc.stewie.queuingsystem.entity.SourceEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SourceRepository extends JpaRepository<SourceEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE sources", nativeQuery = true)
    void truncateTable();
}
