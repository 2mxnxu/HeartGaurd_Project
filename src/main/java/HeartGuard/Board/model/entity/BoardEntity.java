package HeartGuard.Board.model.entity;

import HeartGuard.Board.model.dto.BaseTime;
import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.User.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "install")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ino;

    private String ititle;

    private String icontent;

    @Column(name = "istate", columnDefinition = "INT DEFAULT 0")
    private int istate = 0;

    @ManyToOne
    @JoinColumn(name="uno",referencedColumnName = "uno")
    private UserEntity userEntity;

    public BoardDto toDto() {
        return BoardDto.builder()
                .ino(this.ino)
                .ititle(this.ititle)
                .icontent(this.icontent)
                .istate(this.istate)
                .uno(userEntity.getUno())
                .idate(this.getCreateAt())
                .build();
    }
}
