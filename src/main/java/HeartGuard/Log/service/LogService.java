package HeartGuard.Log.service;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.model.entity.LogEntity;
import HeartGuard.Log.model.repository.LogEntityRepository;
import HeartGuard.Socket.handler.WebSocketHandler_H;
import HeartGuard.Socket.handler.WebSocketHandler_U;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService {
    private final LogEntityRepository logEntityRepository;
    private final HospitalEntityRepository hospitalEntityRepository;
    private final WebSocketHandler_H webSocketHandler;
    private final WebSocketHandler_U webSocketHandlerU;

    // 병원 hid로 해당 병원의 로그만 조회
    public List<LogDto> viewLogByHospital(String hid) {
        HospitalEntity hospital = hospitalEntityRepository.findByHid(hid);
        if (hospital == null) {
            throw new EntityNotFoundException("병원을 찾을 수 없습니다.");
        }
        // 병원에 해당하는 로그를 조회
        List<LogEntity> logs = logEntityRepository.findByHospitalEntityHno(hospital.getHno());
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

        // 병원 서버소켓에게 메시지 보내기.
        try {
            webSocketHandler.handleTextMessage( null ,
                    new TextMessage("\uD83D\uDEA8응급 신고 발생! 즉시 확인 바랍니다.\uD83D\uDEA8"));
        } catch (Exception e) {
            throw new RuntimeException(e);
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
            // 수락했다고 소켓으로 메시지 보내기 WebSocketHandler_U 사용
            String 병원이름 = logOptional.get().getHospitalEntity().getAddress()  + "("+logOptional.get().getHospitalEntity().getHid()+")";
            String 신고자번호 = logOptional.get().getPhone();
            try {
                webSocketHandlerU.handleTextMessage( null , new TextMessage("\uD83D\uDEA8 신고를 수락했습니다. \uD83D\uDEA8" +"/"+ 병원이름 +"/"+ 신고자번호 ));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

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
