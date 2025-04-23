package HeartGuard.Hospital.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDto {
    private int hno;
    private String hid;
    private String hpwd;
    private int apino;

    public HospitalEntity toEntity(){
        return HospitalEntity.builder()
                .hno(hno)
                .hid(hid)
                .hpwd(hpwd)
                .apino(apino)
                .build();
    }
}
