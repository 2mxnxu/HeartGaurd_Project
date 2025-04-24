package HeartGuard.Hospital.model.repository;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HospitalEntityRepository extends JpaRepository<HospitalEntity, Integer> {
    Optional<HospitalEntity> findByApino(Integer apino);
    HospitalEntity findByHid(String hid);
}
