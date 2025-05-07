package HeartGuard.Log.model.repository;

import HeartGuard.Log.model.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LogEntityRepository extends JpaRepository<LogEntity, Integer> {
    List<LogEntity> findByHospitalEntityHno(int hno);  // 병원 hno로 로그 조회
    List<LogEntity> findByLstate(int lstate);  // 상태로 로그 조회
    Optional<LogEntity> findById(int lno);  // lno로 로그 조회
    List<LogEntity> findByPhoneAndLstate(String phone, int lstate);

}
