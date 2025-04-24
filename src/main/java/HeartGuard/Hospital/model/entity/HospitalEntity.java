package HeartGuard.Hospital.model.entity;

import HeartGuard.Hospital.model.dto.HospitalDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospital")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HospitalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hno;
    private String hid;
    private String hpwd;
    @Column(nullable = false)
    private int apino;
    private String type;
    private String name;
    private String location;
    private String tel;
    private String emgTel;
    private String address;

    public HospitalDto toDto(){
        return HospitalDto.builder()
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
                .build();
    }
}
