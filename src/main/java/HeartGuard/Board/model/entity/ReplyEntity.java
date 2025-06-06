package HeartGuard.Board.model.entity;

import HeartGuard.Board.model.dto.BaseTime;
import HeartGuard.Board.model.dto.ReplyDto;
import HeartGuard.User.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ireply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rno;

    private String rcontent;

    @ManyToOne
    @JoinColumn(name="uno",referencedColumnName = "uno")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="bno",referencedColumnName = "bno")
    private BoardEntity boardEntity;

    public ReplyDto toDto(){
        return ReplyDto.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .uno(userEntity.getUno())
               .bno(boardEntity.getBno())
                .build();
    }



}
