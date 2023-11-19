package inc.stewie.queuingsystem.repository;

import inc.stewie.queuingsystem.entity.SourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceRepository extends JpaRepository<SourceEntity, Integer> {
}
