package HeartGuard.Announce.Board.model.respository;

import HeartGuard.Announce.Board.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardEntityRepository extends JpaRepository<BoardEntity,Integer> {
}
