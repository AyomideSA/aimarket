package app.aimarket.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 07/02/2023, Tuesday
 **/
@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void save(@ModelAttribute User user) {
    userRepository.save(user);
  }

  public List<User> getStudents() {
    return userRepository.findAll();
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  // Liwia Implements this
  // Just checks for if user already exits in db,
  // If details are valid etc
  public boolean ValidUser(@ModelAttribute User user) {
    return true;
  }

}
