package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.ImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImgEntityRepository
        extends JpaRepository<ImgEntity,Long> {
}
