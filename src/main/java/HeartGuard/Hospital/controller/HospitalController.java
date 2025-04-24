package HeartGuard.Hospital.controller;


import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalController {

    private final HospitalService hospitalService;

    @PostMapping("/merge")
    public List<HospitalDto> mergeHospitals(@RequestBody String jsonData) throws IOException {
        return hospitalService.mergeHospitalInfo(jsonData);
    }
}
