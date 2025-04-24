package HeartGuard.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalInfoDTO {
    private String type;
    private String name;
    private String location;
    private String tel;
    private String emgTel;
    private String address;
    private Integer apino;

    // 로그인 정보 연동
    private String hid;
    private String hpwd;
    private long hno;
}
