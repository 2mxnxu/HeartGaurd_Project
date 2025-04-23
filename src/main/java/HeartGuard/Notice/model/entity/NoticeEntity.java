package HeartGuard.Notice.model.entity;

import HeartGuard.Notice.model.dto.BaseTime;
import HeartGuard.Notice.model.dto.NoticeDto;
import HeartGuard.User.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adboard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adbno;

    private String adbtitle;

    private String adbcontent;

    @ManyToOne
    @JoinColumn(name="uno",referencedColumnName = "uno")
    private UserEntity userEntity;

    public NoticeDto toDto() {
        return NoticeDto.builder()
                .adbno(this.adbno)
                .adbtitle(this.adbtitle)
                .adbcontent(this.adbcontent)
                .uno(userEntity.getUno())
                .build();
    }

}
