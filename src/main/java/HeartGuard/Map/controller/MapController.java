package HeartGuard.Map.controller;

import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Map.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MapController {

    private final MapService mapService;

    @GetMapping("/gethospital")
    public ResponseEntity<List<HospitalDto>> getHospital(){
        return mapService.getHospital();
    }
}
