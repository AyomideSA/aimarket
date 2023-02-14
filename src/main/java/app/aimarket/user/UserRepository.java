package app.aimarket.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends
    JpaRepository<User, Long> {

  User findByUsername(String userrname);
  User findByPassword(String password);
  User findByEmail(String email);

}
