package HeartGuard.Notice.model.dto;

import HeartGuard.Notice.model.entity.NoticeEntity;
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
public class NoticeDto {
    private int adbno;
    private String adbtitle;
    private String adbcontent;
    private int uno;


    public NoticeEntity toEntity(UserEntity userEntity){
        return NoticeEntity.builder()
                .adbno(this.adbno)
                .adbtitle(this.adbtitle)
                .adbcontent(this.adbcontent)
                .userEntity(userEntity)
                .build();
    }
}
