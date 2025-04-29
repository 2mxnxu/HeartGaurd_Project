package HeartGuard.Log.controller;

import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/log")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LogController {
    private final LogService logService;
    @GetMapping("/view")
    public LogDto viewLog(@RequestParam int hno){
        LogDto logDto = logService.viewLog(hno);
        if (logDto == null) {
            return null;
        }else {
            return logDto;
        }
    }
    @PostMapping("/submit")
    public String submit(
            @RequestParam int uno,
            @RequestParam double llat,
            @RequestParam double llong) {

        try {
            logService.submit(uno, llat, llong);
            return "신청";
        } catch (Exception e) {
            return "오류 " + e.getMessage();
        }
    }
}
