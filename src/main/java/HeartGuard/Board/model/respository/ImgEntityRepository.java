
package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.ImgEntity;
import HeartGuard.Board.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImgEntityRepository extends JpaRepository<ImgEntity, Long> {
    // 특정 게시글에 속한 이미지들 조회
    List<ImgEntity> findByBoardEntity(BoardEntity boardEntity);
}
