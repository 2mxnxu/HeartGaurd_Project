package HeartGuard.Log.model.repository;

import HeartGuard.Log.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {
}
