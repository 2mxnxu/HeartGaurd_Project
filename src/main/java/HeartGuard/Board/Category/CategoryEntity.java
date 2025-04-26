package HeartGuard.Board.Category;

import HeartGuard.Board.model.dto.BaseTime;
import HeartGuard.Board.model.entity.BoardEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseTime {
    @Id // preimary key
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private long cno; // 카테고리 식별번호

    @Column( nullable = false , length = 100 ) // not null , 최대길이 :100
    private String cname; // 카테고리 이름


}