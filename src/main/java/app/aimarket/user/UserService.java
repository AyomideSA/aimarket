package app.aimarket.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.validation.constraints.Null;
import java.sql.SQLOutput;
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

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
  public User findUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }
  public User findUserByPassword(String password) {
    return userRepository.findByPassword(password);
  }

  public static boolean isValidEmailAddress(String email) {
    boolean result = true;
    try {
      InternetAddress emailAddr = new InternetAddress(email);
      emailAddr.validate();
    } catch (AddressException ex) {
      result = false;
    }
    return result;
  }

  public boolean ValidUser(@ModelAttribute User user){
    if(findUserByUsername(user.getUsername()) != null){
      return false;
    }
    else if(findUserByEmail(user.getEmail()) != null){
      return false;
    }
    else if(isValidEmailAddress(user.getEmail())){
      return false;
    }

    return true;
  }

}
