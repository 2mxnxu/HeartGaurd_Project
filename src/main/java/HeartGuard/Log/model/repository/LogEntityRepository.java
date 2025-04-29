package HeartGuard.Log.model.repository;

import HeartGuard.Log.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {
    List<LogEntity> findByHospitalEntity_Hno(int hno);

}
