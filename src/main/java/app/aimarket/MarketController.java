package app.aimarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MarketController {
  private final UserService userService;

  @Autowired
  public MarketController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/aimarket/home")
  public String viewHomePage() {
    return "home.html";
  }

  @PostMapping("aimarket/register")
  public String signup(@ModelAttribute User user) {
    userService.save(user);
    return "redirect:/aimarket/home";
  }

}
