package HeartGuard.Hospital.controller;


import HeartGuard.Aed.service.AedService;
import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalController {

    private final HospitalService hospitalService;
    private final AedService aedService;


    @PutMapping("/put")
    public Map<String, Object> postHospitals() {
        Map<String, Object> jsonData = aedService.test2(); // 외부 API 호출
        return hospitalService.postHospitals(jsonData);    // DB 저장 후 결과 반환
    }

}
