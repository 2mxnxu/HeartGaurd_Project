package HeartGuard.Board.Category;

import HeartGuard.Board.model.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseTime {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // auto_increment
    private long cno;

    @Column( nullable = false , length = 100 )
    private String cname;


}