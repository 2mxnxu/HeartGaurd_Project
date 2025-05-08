package HeartGuard.Map.service;

import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MapService {
    private final HospitalEntityRepository hospitalEntityRepository;

    public ResponseEntity<List<HospitalDto>> getHospital() {
        List<HospitalDto> hospitalDtoList = hospitalEntityRepository.findAll()
                .stream()
                .map(HospitalEntity::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(hospitalDtoList);
    }
}
