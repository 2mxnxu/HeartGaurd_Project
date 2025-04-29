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
        List<LogEntity> logs = logEntityRepository.findByHospitalEntityHno(hno);
        return logs.stream()
                .map(LogDto::toDto)
                .collect(Collectors.toList());
    }
    public void submit(String phone, double llat, double llong) {
        List<HospitalEntity> hospitals = hospitalEntityRepository.findAll();

        for (HospitalEntity hospitalEntity : hospitals) {
            LogEntity logEntity = LogEntity.builder()
                    .llat(llat)
                    .llong(llong)
                    .lstate(2)
                    .phone(phone)
                    .hospitalEntity(hospitalEntity)
                    .build();
            logEntityRepository.save(logEntity);
        }
    }
    public String updateLog(int lno, int lstate) {
        Optional<LogEntity> logOptional = logEntityRepository.findById(lno);
        if (logOptional.isEmpty()) {
            return "호출 내역을 찾을 수 없습니다.";
        }
        LogEntity log = logOptional.get();
        if (log.getLstate() != 2) {
            return "수락할 수 있는 게 없습니다.";
        }

        // lstate를 1 또는 0으로 업데이트
        if (lstate == 1) {
            log.setLstate(1);
        } else if (lstate == 0) {
            log.setLstate(0);
        }
        logEntityRepository.save(log);

        // 전체 로그에서 lstate가 2인 것들을 찾음
        List<LogEntity> others = logEntityRepository.findByLstate(2);

        for (LogEntity other : others) {
            if (other.getLno() != lno) {
                other.setLstate(0);
                logEntityRepository.save(other);
            }
        }
        return "완료";
    }
}
