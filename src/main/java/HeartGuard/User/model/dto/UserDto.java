package HeartGuard.User.model.dto;

import HeartGuard.User.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private int uno;          // auto_increment (PK)
    private String uid;        // 아이디
    private String upwd;       // 비밀번호
    private String uname;      // 이름
    private int ustate;        // 상태 (기본값 0)
    private String uphone;     // 전화번호

    public UserEntity toEntity(){
        return UserEntity.builder()
                .uno(this.uno)
                .uid(this.uid)
                .upwd(this.upwd)
                .uname(this.uname)
                .ustate(this.ustate)
                .uphone(this.uphone)
                .build();
    }
}
