package app.aimarket.user;

import app.aimarket.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
    JpaRepository<User, Long> {

  User findByEmail(String user);

}
