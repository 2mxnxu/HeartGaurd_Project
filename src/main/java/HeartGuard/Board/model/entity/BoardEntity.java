package HeartGuard.Board.model.entity;

import HeartGuard.Board.model.dto.BaseTime;
import HeartGuard.Board.model.dto.BoardDto;
import HeartGuard.Board.Category.CategoryEntity;
import HeartGuard.User.model.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bno;

    private String btitle;

    private String bcontent;

    @Column(name = "bview", columnDefinition = "INT DEFAULT 0")
    private int bview = 0;

    @ManyToOne
    @JoinColumn(name="cno",referencedColumnName = "cno")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name="uno",referencedColumnName = "uno")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "boardEntity" , cascade = CascadeType.ALL , fetch = FetchType.LAZY )
    @ToString.Exclude @Builder.Default
    private List<ImgEntity> imgEntityList = new ArrayList<>();

    @OneToMany( mappedBy = "boardEntity" , cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @ToString.Exclude @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();


//    public BoardDto toDto() {
//        return BoardDto.builder()
//                .bno(this.bno)
//                .btitle(this.btitle)
//                .bcontent(this.bcontent)
//                .bview(this.bview)
//                .cno(categoryEntity.getCno())
//                .uno(userEntity.getUno())
//                .createAt(this.getCreateAt())
//                .build();
//    }
}
