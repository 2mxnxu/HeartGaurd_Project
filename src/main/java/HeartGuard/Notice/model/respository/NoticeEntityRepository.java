package HeartGuard.Notice.model.respository;

import HeartGuard.Notice.model.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeEntityRepository extends JpaRepository<NoticeEntity,Integer> {
}
