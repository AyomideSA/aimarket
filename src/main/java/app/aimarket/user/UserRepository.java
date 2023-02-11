package app.aimarket.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
    JpaRepository<User, Long> {

  User findByUsername(String user);
  User findByPassword(String user);
  User findByEmail(String user);

}
