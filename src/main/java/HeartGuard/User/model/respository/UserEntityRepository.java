package HeartGuard.User.model.respository;


import HeartGuard.User.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity,Integer> {
}
