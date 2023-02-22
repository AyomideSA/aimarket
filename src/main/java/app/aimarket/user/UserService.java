package app.aimarket.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public boolean ValidUser(@ModelAttribute User user){
    if(Objects.equals(user.getUsername(), "") ||findUserByUsername(user.getUsername()) != null){
      System.out.println("the username name is: "+user.getUsername());
      return false;
    }
    else if(Objects.equals(user.getPassword(), "")){
      System.out.println("the password is: "+user.getPassword());
      return false;
    }
    else if(Objects.equals(user.getName(), "")){
      System.out.println("the name is: "+user.getName());
      return false;
    }
    else if(findUserByEmail(user.getEmail()) != null){
      System.out.println("the email is 1: "+user.getEmail());
      return false;
    }
    else if(!isValidEmailAddress(user.getEmail())){
      System.out.println("the email is 2: "+user.getEmail());
      return false;
    }

    return true;
  }

  public boolean signinUserValid(@ModelAttribute User user, String email, String password){
    if(Objects.equals(email, "") ||findUserByEmail(email) == null){
      System.out.println("the email is: "+user.getEmail());
      return false;
    }
    else if(Objects.equals(password, "")||findUserByPassword(password)==null){
      System.out.println("the password is: "+user.getPassword());
      return false;
    }

    return true;
  }

}
