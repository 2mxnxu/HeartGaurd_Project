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
    private double llat;
    private double llong;
    private int lstate;
    private String phone;
    private int hno;
    private LocalDateTime create_at;

    public LogEntity toEntity(HospitalEntity hospitalEntity){
        return LogEntity.builder()
                .lno(this.lno)
                .llat(this.llat)
                .llong(this.llong)
                .lstate(this.lstate)
                .phone(this.phone)
                .hospitalEntity(hospitalEntity)
                .build();
    }

    public static LogDto toDto(LogEntity logEntity) {
        return LogDto.builder()
                .lno(logEntity.getLno())
                .llat(logEntity.getLlat())
                .llong(logEntity.getLlong())
                .lstate(logEntity.getLstate())
                .phone(logEntity.getPhone())
                .create_at(logEntity.getCreateAt())
                .hno(logEntity.getHospitalEntity().getHno())
                .build();
    }
}
