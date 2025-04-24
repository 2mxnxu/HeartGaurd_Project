package HeartGuard.Hospital.service;

import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalEntityRepository hospitalEntityRepository;


    public Map<String, Object> postHospitals(Map<String, Object> jsonData) {
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) jsonData.get("data");

        for (Map<String, Object> data : dataList) {
            String emgTel = (String) data.get("전화번호(응급시 연락)");

            // 1. 응급전화번호에서 숫자만 추출하고 마지막 4자리 가져오기
            String digits = emgTel.replaceAll("[^0-9]", "");
            if (digits.length() < 4) continue; // 길이 체크
            String last4 = digits.substring(digits.length() - 4);
            Integer apino = Integer.parseInt(last4);

            // 2. apino로 병원 찾기
            Optional<HospitalEntity> existingOpt = hospitalEntityRepository.findByApino(apino);

            // 3. 병원이 있는 경우에만 업데이트
            if (existingOpt.isPresent()) {
                HospitalEntity hospital = existingOpt.get();

                hospital.setType((String) data.get("구분(권역응급 의료센터_지역응급 의료센터)"));
                hospital.setName((String) data.get("병원명"));
                hospital.setLocation((String) data.get("위치"));
                hospital.setTel((String) data.get("전화번호"));
                hospital.setEmgTel(emgTel);
                hospital.setAddress((String) data.get("주소"));

                hospitalEntityRepository.save(hospital); //  update only
            }
        }


        return Map.of(
                "status", "success",
                "message", "병원 정보 저장 완료",
                "count", dataList.size()
        );
    }

}


