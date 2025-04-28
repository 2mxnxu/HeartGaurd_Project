package HeartGuard.Board.model.dto;

<<<<<<<< HEAD:src/main/java/HeartGuard/Log/model/entity/BaseTime.java
package HeartGuard.Log.model.entity;
========
package HeartGuard.Board.model.entity;
>>>>>>>> origin/ryu:src/main/java/HeartGuard/Board/model/entity/BaseTime.java


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // 해당 클래스는 일반 엔티티가 아닌 상속(공통부문) 엔티티 용도
@EntityListeners(AuditingEntityListener.class) // 해당 클래스를 JPA 감사 활성화
@Getter
public class BaseTime {
    // 1. 생성날짜
    @CreatedDate // 엔티티의 생성 날짜/시간 주입
    private LocalDateTime createAt;

}