package HeartGuard.Hospital.model.repository;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalEntityRepository extends JpaRepository<HospitalEntity, Integer> {
    Optional<HospitalEntity> findByApino(Integer apino);
    HospitalEntity findByHid(String hid);

    Optional<HospitalEntity> findByHno(int hno);
}
