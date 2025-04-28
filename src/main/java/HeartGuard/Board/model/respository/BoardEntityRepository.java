package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity,Long> {
//    List<BoardEntity> findByCategoryEntityCno(long cno);

//    @Query(value = "select  * from board where cno = :cno"),nativeQuery = true)
//List<BoardEntity> nativeQuery1(long bno);
}

