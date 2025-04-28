package HeartGuard.Board.model.dto;

import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.Category.CategoryEntity;
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
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private long cno;
    private int uno;
    private LocalDateTime createAt;

    public BoardEntity toEntity(UserEntity userEntity,CategoryEntity categoryEntity){
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .categoryEntity(categoryEntity)
                .userEntity(userEntity)
                .build();
    }
}
