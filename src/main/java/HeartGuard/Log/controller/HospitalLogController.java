package HeartGuard.Log.controller;

import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Hospital.service.HospitalService;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.Log.service.HospitalLogService;
import HeartGuard.Util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HospitalLogController {
    private final HospitalLogService hospitalLogService;
    private final JwtUtil jwtUtil;

    @GetMapping("/all")
    public ResponseEntity<List<LogDto>> viewHospitalLog(){
        List<LogDto> viewHospitalLogList = hospitalLogService.viewHospitalLog();
        return ResponseEntity.status(200).body(viewHospitalLogList);
    }
}
