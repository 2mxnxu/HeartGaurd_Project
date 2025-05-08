package HeartGuard.Log.service;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.model.entity.LogEntity;
import HeartGuard.Log.model.repository.LogEntityRepository;
import HeartGuard.Socket.handler.WebSocketHandler;
import jakarta.persistence.EntityNotFoundException;
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
    private final WebSocketHandler webSocketHandler;

    // 병원 hid로 해당 병원의 로그만 조회
    public List<LogDto> viewLogByHospital(String hid) {
        HospitalEntity hospital = hospitalEntityRepository.findByHid(hid);
        if (hospital == null) {
            throw new EntityNotFoundException("병원을 찾을 수 없습니다.");
        }

        // 병원에 해당하는 로그를 조회
        List<LogEntity> logs = logEntityRepository.findByHospitalEntityHno(hospital.getHno());

        webSocketHandler.enterHospitalSocket(hid);

        return logs.stream().map(LogDto::toDto).collect(Collectors.toList());
    }

    public void submit(String phone, double llat, double llong) {
        // 동일한 전화번호로 상태가 2인 로그가 이미 존재하는지 확인
        List<LogEntity> existingLogs = logEntityRepository.findByPhoneAndLstate(phone, 2);
        if (!existingLogs.isEmpty()) {
            // 이미 대기 중인 로그가 존재하면 새로운 로그를 생성하지 않음
            return;
        }

        // 새로운 로그 생성
        List<HospitalEntity> hospitals = hospitalEntityRepository.findAll();
        for (HospitalEntity hospitalEntity : hospitals) {
            LogEntity logEntity = LogEntity.builder()
                    .llat(llat)
                    .llong(llong)
                    .lstate(2)  // 초기 상태 2
                    .phone(phone)
                    .hospitalEntity(hospitalEntity)
                    .build();
            logEntityRepository.save(logEntity);
        }
    }


    // 로그 상태 업데이트
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
        log.setLstate(lstate);
        logEntityRepository.save(log);

        // lstate가 1인 경우만 다른 로그들을 0으로 변경
        if (lstate == 1) {
            // 전체 로그에서 lstate가 2인 것들을 찾음
            List<LogEntity> others = logEntityRepository.findByLstate(2);

            for (LogEntity other : others) {
                if (other.getLno() != lno) {
                    other.setLstate(0);  // 다른 상태로 설정
                    logEntityRepository.save(other);
                }
            }
        }
        return "완료";
    }
}
