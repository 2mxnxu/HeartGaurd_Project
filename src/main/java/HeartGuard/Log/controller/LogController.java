package HeartGuard.Log.controller;

import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.service.LogService;
import HeartGuard.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LogController {
    private final LogService logService;
    private final JwtUtil jwtUtil;

    // 병원 로그 조회 API
    @GetMapping("/view")
    public List<LogDto> viewLogByHospital(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.startsWith("Bearer ") ? authorizationHeader.substring(7) : authorizationHeader;

        // hid만 추출
        String hid = jwtUtil.validateToken2(token);
        if (hid == null) {
            return List.of();  // 유효하지 않으면 빈 리스트 반환
        }

        return logService.viewLogByHospital(hid);  // hid 전달
    }

    // 로그 상태 업데이트
    @PostMapping("/state")
    public String updateLog(@RequestBody Map<String, Integer> requestData) {
        int lno = requestData.get("lno");
        int lstate = requestData.get("lstate");
        try {
            String result = logService.updateLog(lno, lstate);
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // 로그 제출 API
    @PostMapping("/submit")
    public String submit(@RequestBody LogDto logDto) {
        try {
            logService.submit(logDto.getPhone(), logDto.getLlat(), logDto.getLlong());
            return "신청";
        } catch (Exception e) {
            return "오류 " + e.getMessage();
        }
    }
}
