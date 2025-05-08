package HeartGuard.Map.service;

import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MapService {
    private final HospitalEntityRepository hospitalEntityRepository;

//    @GetMapping("/gethospital")
//    public ResponseEntity<List<HospitalDto>> getHospital(HospitalDto hospitalDto){
//        return hospitalEntityRepository.getHospital();
//    }
}
