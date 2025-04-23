package HeartGuard.Log.model.entity;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Log.model.dto.LogDto;
import jakarta.persistence.*;
import lombok.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;

@Entity
@Table(name = "hlog")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lno;
    private String lloc;
    private LocalDateTime ldate;

    @ManyToOne
    @JoinColumn(name = "uno", referencedColumnName = "uno")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "hno", referencedColumnName = "hno")
    private HospitalEntity hospitalEntity;

    public LogDto toDto(){
        return LogDto.builder()
                .lno(lno)
                .lloc(lloc)
                .ldate(ldate)
                .uno(userEntity.getUno())
                .hno(hospitalEntity.getHno())
                .build();
    }
}
