package HeartGuard.Hospital.model.repository;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalEntityRepository extends JpaRepository<HospitalEntity, Integer> {
}
