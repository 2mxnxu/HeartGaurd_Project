package HeartGuard.Log.model.repository;

import HeartGuard.Log.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {
    List<LogEntity> findByHospitalEntityHno(int hno);
    List<LogEntity> findByLstate(int lstate);
    Optional<LogEntity> findById(int lno);
}
