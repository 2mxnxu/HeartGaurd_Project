package HeartGuard.Board.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryEntityRepository
        extends JpaRepository<CategoryEntity,Long> {
}
