package HeartGuard.Log.model.dto;

import HeartGuard.Hospital.model.entity.HospitalEntity;
import HeartGuard.Log.model.entity.LogEntity;
import HeartGuard.User.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {
    private int lno;
    private String lloc;
    private LocalDateTime ldate;
    private int uno;
    private int hno;

    public LogEntity toEntity(UserEntity userEntity, HospitalEntity hospitalEntity){
        return LogEntity.builder()
                .lno(this.lno)
                .lloc(this.lloc)
                .ldate(this.ldate)
                .userEntity(userEntity)
                .hospitalEntity(hospitalEntity)
                .build();
    }
}
