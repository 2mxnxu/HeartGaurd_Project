package HeartGuard.Log.service;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.model.entity.LogEntity;
import HeartGuard.Log.model.repository.LogEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HospitalLogService {
    private final HospitalEntityRepository hospitalEntityRepository;
    private final LogEntityRepository logEntityRepository;


    public List<LogDto> viewHospitalLog(){
        List<LogEntity> viewLogList ;
        viewLogList = logEntityRepository.findAll();
        return viewLogList.stream()
                .map(LogDto::toDto)
                .collect(Collectors.toList());
    }
}
