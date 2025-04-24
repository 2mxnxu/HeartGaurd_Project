package HeartGuard.model.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hospital")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalTestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hno;

    private String hid;
    private String hpwd;
    private Integer apino;
}
