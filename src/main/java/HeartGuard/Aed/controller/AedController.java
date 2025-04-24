package HeartGuard.Aed.controller;

import HeartGuard.Aed.service.AedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AedController {

    private final AedService aedService;

    @GetMapping("/api1")
    public String getAedInfo() {
        aedService.test();
        return "AED 정보 콘솔 출력";
    }

    @GetMapping("/api2")
    public String getHospitalInfo(){
        aedService.test2();
        return "병원 정보 콘솔 출력";
    }
}
