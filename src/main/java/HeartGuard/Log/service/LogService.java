package HeartGuard.Log.service;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.model.entity.LogEntity;
import HeartGuard.Log.model.repository.LogEntityRepository;
import HeartGuard.User.model.entity.UserEntity;
import HeartGuard.User.model.respository.UserEntityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService {
    private final LogEntityRepository logEntityRepository;
    private final HospitalEntityRepository hospitalEntityRepository;
    private final UserEntityRepository userEntityRepository;
    public LogDto viewLog(int hno) {
        Optional<LogEntity> logEntityOptional = logEntityRepository.findById(hno);
        if (logEntityOptional.isEmpty()) return null;
        LogEntity logEntity = logEntityOptional.get();
        return LogDto.toDto(logEntity);
    }
    public void submit(int uno, double llat, double llong) {
        Optional<UserEntity> userOptional = userEntityRepository.findById(uno);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        UserEntity userEntity = userOptional.get();

        List<HospitalEntity> hospitals = hospitalEntityRepository.findAll();

        for (HospitalEntity hospitalEntity : hospitals) {
            LogEntity logEntity = LogEntity.builder()
                    .llat(llat)
                    .llong(llong)
                    .lstate(2)
                    .userEntity(userEntity)
                    .hospitalEntity(hospitalEntity)
                    .build();
            logEntityRepository.save(logEntity);
        }
    }
}
