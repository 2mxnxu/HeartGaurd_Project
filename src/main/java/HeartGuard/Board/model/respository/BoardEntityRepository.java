package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntityRepository extends JpaRepository<BoardEntity,Integer> {
}
