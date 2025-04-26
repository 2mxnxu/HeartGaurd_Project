package HeartGuard.User.model.entity;

import HeartGuard.User.model.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uno;
    private String uid;
    private String upwd;
    private String uname;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int ustate = 0;

    private String uphone;

    public UserDto toDto() {
        return UserDto.builder()
                .uno(this.uno)
                .uid(this.uid)
                .upwd(this.upwd)
                .uname(this.uname)
                .ustate(this.ustate)
                .uphone(this.uphone)
                .build();
    }
}
