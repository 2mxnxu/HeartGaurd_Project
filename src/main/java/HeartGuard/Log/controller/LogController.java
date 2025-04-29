package HeartGuard.Log.controller;

import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping( "/log")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LogController {
    private final LogService logService;
    @GetMapping("/view")
    public List<LogDto> viewLogByHospital(@RequestParam int hno){
        return logService.viewLogByHospital(hno);
    }

    @PostMapping("/submit")
    public String submit(@RequestBody LogDto logDto) {
        try {
            logService.submit(logDto.getPhone(), logDto.getLlat(), logDto.getLlong());
            return "신청";
        } catch (Exception e) {
            return "오류 " + e.getMessage();
        }
    }

    @PostMapping("/state")
    public String updateLog(@RequestBody Map<String, Integer> requestData) {
        int lno = requestData.get("lno");
        int lstate = requestData.get("lstate");
        try {
            String result = logService.updateLog(lno, lstate);
            return result;
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
