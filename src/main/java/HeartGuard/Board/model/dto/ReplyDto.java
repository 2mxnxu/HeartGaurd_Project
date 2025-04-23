package HeartGuard.Board.model.dto;

import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.model.entity.ReplyEntity;
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
public class ReplyDto {
    private int rno;
    private String rcontent;
    private LocalDateTime rdate;
    private int uno;
    private int ino;

    public ReplyEntity toEntity(UserEntity userEntity, BoardEntity boardEntity){
        return ReplyEntity.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .userEntity(userEntity)
                .boardEntity(boardEntity)
                .build();
    }

}
