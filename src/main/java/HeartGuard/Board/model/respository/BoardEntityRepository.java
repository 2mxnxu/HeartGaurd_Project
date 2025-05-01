package HeartGuard.Board.model.respository;

import HeartGuard.Board.model.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardEntityRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findByCategoryEntityCno(long cno);
    @Query( value = "SELECT * FROM board " +
            " WHERE ( :cno IS NULL OR :cno = 0 OR cno = :cno ) " +
            " AND ( :keyword IS NULL OR btitle LIKE %:keyword% )" , nativeQuery = true)
    Page<BoardEntity> findBySearch(Long cno , String keyword , Pageable pageable );



}

