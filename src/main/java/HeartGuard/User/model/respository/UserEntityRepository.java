package HeartGuard.User.model.respository;


import HeartGuard.User.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUid(String uid);
    Page<UserEntity> findByUnameContaining(String keyword, Pageable pageable);
    Optional<UserEntity> findByUno(Integer uno);
}
