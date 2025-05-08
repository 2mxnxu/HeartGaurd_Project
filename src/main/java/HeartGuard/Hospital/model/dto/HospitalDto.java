package HeartGuard.Hospital.model.dto;

import HeartGuard.Hospital.model.entity.HospitalEntity;
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
    private String type;
    private String name;
    private String location;
    private String tel;
    private String emgTel;
    private String address;

    private double hlat;
    private double hlong;

    public HospitalEntity toEntity(){
        return HospitalEntity.builder()
                .hno(hno)
                .hid(hid)
                .hpwd(hpwd)
                .apino(apino)
                .type(type)
                .name(name)
                .location(location)
                .tel(tel)
                .emgTel(emgTel)
                .address(address)
                .hlat(hlat)
                .hlong(hlong)
                .build();
    }
}
