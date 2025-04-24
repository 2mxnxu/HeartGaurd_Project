package HeartGuard.model.repository;

import HeartGuard.model.entity.HospitalTestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalRepository extends JpaRepository<HospitalTestEntity, Long> {
    Optional<HospitalTestEntity> findByApino(Integer apino);
}
