package HeartGuard.Board.model.dto;

import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.model.entity.ImgEntity;
import HeartGuard.Board.model.entity.ReplyEntity;
import HeartGuard.User.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
    private long rno;
    private String rcontent;
    private LocalDateTime rdate;
    private long uno;
    private long bno;
    private String uname;

    public ReplyEntity toEntity(BoardEntity boardEntity, UserEntity userEntity) {
        return ReplyEntity.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .boardEntity(boardEntity)
                .userEntity(userEntity)  // 여기에 userEntity 설정
                .build();
    }


    public static ReplyDto toDto(ReplyEntity replyEntity){
        return ReplyDto.builder()
                .rno(replyEntity.getRno())
                .rcontent(replyEntity.getRcontent())
                .bno(replyEntity.getBoardEntity().getBno())
                .uno(replyEntity.getUserEntity().getUno())
                .uname(replyEntity.getUserEntity().getUname())
                .build();
    }

}
