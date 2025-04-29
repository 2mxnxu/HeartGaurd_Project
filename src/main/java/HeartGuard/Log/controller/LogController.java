package HeartGuard.Log.controller;

import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            logService.submit(logDto.getLlat(), logDto.getLlong());
            return "신청";
        } catch (Exception e) {
            return "오류 " + e.getMessage();
        }
    }
}
