package HeartGuard.Hospital.service;

import HeartGuard.Hospital.model.dto.HospitalDto;
import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Hospital.model.repository.HospitalEntityRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalEntityRepository hospitalEntityRepository;

    // JSON 파싱 예시 (공공데이터 String으로 받음)
    public List<HospitalDto> mergeHospitalInfo(String jsonData) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonData);
        List<HospitalDto> result = new ArrayList<>();

        for (JsonNode node : root) {
            HospitalDto dto = new HospitalDto();
            System.out.println(node);
            dto.setType(node.get("구분(권역응급 의료센터_지역응급 의료센터)").asText());
            dto.setName(node.get("병원명").asText());
            dto.setLocation(node.get("위치").asText());
            dto.setTel(node.get("전화번호").asText());
            dto.setEmgTel(node.get("전화번호(응급시 연락)").asText());
            dto.setAddress(node.get("주소").asText());

            // 응급전화번호 → 숫자만 추출하여 apino로 변환
            String emg = node.get("전화번호(응급시 연락)").asText().replaceAll("[^0-9]", "");
            Integer apino = Integer.parseInt(emg);
            dto.setApino(apino);

            // 병원 테이블에서 일치하는 로그인 정보 찾기
            Optional<HospitalEntity> match = hospitalEntityRepository.findByApino(apino);
            match.ifPresent(h -> {
                dto.setHid(h.getHid());
                dto.setHpwd(h.getHpwd());
            });

            result.add(dto);
        }

        return result;
    }
}
