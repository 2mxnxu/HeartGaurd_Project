package HeartGuard.controller;


import HeartGuard.model.dto.HospitalInfoDTO;
import HeartGuard.service.HospitalService;
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
    public List<HospitalInfoDTO> mergeHospitals(@RequestBody String jsonData) throws IOException {
        return hospitalService.mergeHospitalInfo(jsonData);
    }
}
