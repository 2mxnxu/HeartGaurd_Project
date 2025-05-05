package HeartGuard.Board.model.dto;

import HeartGuard.Board.model.entity.BoardEntity;
import HeartGuard.Board.Category.CategoryEntity;
import HeartGuard.Board.model.entity.ImgEntity;
import HeartGuard.User.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
    private long bno;
    private String btitle;
    private String bcontent;
    private int bview;
    private String bwriter;
    private long cno;
    private int uno;
    private LocalDateTime createAt;
    private List<MultipartFile> files = new ArrayList<>();

//    public BoardEntity toEntity(UserEntity userEntity,CategoryEntity categoryEntity){
public BoardEntity toEntity(){
    return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
//                .categoryEntity(categoryEntity)
//                .userEntity(userEntity)
                .build();
    }

    private String cname;
    private List< String > images = new ArrayList<>();

    public static BoardDto toDto(BoardEntity boardEntity){
        return BoardDto.builder()
                .bno(boardEntity.getBno())
                .btitle(boardEntity.getBtitle())
                .bcontent(boardEntity.getBcontent())
                .bview(boardEntity.getBview())
                .cno(boardEntity.getCategoryEntity().getCno())
                .cname(boardEntity.getCategoryEntity().getCname())
                .uno(boardEntity.getUserEntity().getUno())
                .bwriter(boardEntity.getUserEntity().getUname())
                .createAt(boardEntity.getCreateAt())
                .images(boardEntity.getImgEntityList().stream()
                        .map(ImgEntity::getIname)
                        .collect(Collectors.toList())
                )
                .build();
    }

}
