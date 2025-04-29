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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService {
    private final LogEntityRepository logEntityRepository;
    private final HospitalEntityRepository hospitalEntityRepository;
    private final UserEntityRepository userEntityRepository;
    public List<LogDto> viewLogByHospital(int hno) {
        List<LogEntity> logs = logEntityRepository.findByHospitalEntity_Hno(hno);
        return logs.stream()
                .map(LogDto::toDto)
                .collect(Collectors.toList());
    }
    public void submit(double llat, double llong) {
        List<HospitalEntity> hospitals = hospitalEntityRepository.findAll();

        for (HospitalEntity hospitalEntity : hospitals) {
            LogEntity logEntity = LogEntity.builder()
                    .llat(llat)
                    .llong(llong)
                    .lstate(2)
                    .hospitalEntity(hospitalEntity)
                    .build();
            logEntityRepository.save(logEntity);
        }
    }


}
