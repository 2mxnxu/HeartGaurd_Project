package HeartGuard.Log.model.entity;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Log.model.dto.LogDto;
import HeartGuard.User.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hlog")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lno;
    private double llat;
    private double llong;
    private int lstate;

    @ManyToOne
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "hno", referencedColumnName = "hno")
    private HospitalEntity hospitalEntity;

    public LogDto toDto(){
        return LogDto.builder()
                .lno(lno)
                .llat(llat)
                .llong(llong)
                .lstate(lstate)
                .uno(userEntity.getUno())
                .hno(hospitalEntity.getHno())
                .build();
    }
}
