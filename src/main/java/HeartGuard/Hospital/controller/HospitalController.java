package HeartGuard.Hospital.controller;


import HeartGuard.Aed.service.AedService;
import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody HospitalDto hospitalDto){
        String token =hospitalService.login(hospitalDto);
        if(token != null){return ResponseEntity.status(200).body(token);}
        else {return ResponseEntity.status(401).body("로그인실패");}
    }

    @GetMapping("/info")
    public ResponseEntity<HospitalDto> info(@RequestHeader("Authorization") String token){
        System.out.println(token);
        HospitalDto hospitalDto = hospitalService.info(token);
        if(hospitalDto != null){return ResponseEntity.status(200).body(hospitalDto);}
        else {return ResponseEntity.status(403).build();}
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token){
        hospitalService.logout(token);
        return ResponseEntity.status(204).build();
    }
}
