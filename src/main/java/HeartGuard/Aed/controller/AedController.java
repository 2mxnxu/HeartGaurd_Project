package HeartGuard.Aed.controller;

import HeartGuard.Aed.service.AedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AedController {

    private final AedService aedService;

    @GetMapping("/api1")
    public List<Map<String, Object>> getAedInfo() {
        // 공공데이터 API 호출 결과 반환
        return aedService.getAedInfo();
    }

    @GetMapping("/api2")
    public Map<String, Object> getHospitalInfo(){
        return aedService.test2();
    }
}
