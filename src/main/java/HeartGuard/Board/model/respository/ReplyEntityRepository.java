package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.ImgEntity;
import HeartGuard.Board.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyEntityRepository
    extends JpaRepository<ReplyEntity,Long> {

}
