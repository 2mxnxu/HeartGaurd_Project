package HeartGuard.Board.model.dto;

import HeartGuard.Board.model.entity.BoardEntity;
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
    private int ino;
    private String ititle;
    private String icontent;
    private LocalDateTime idate;
    private int istate;
    private int uno;

    public BoardEntity toEntity(UserEntity userEntity){
        return BoardEntity.builder()
                .ino(this.ino)
                .ititle(this.ititle)
                .icontent(this.icontent)
                .istate(this.istate)
                .userEntity(userEntity)
                .build();
    }
}
