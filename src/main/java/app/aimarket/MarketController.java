package app.aimarket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/aimarket")
public class MarketController {

  @GetMapping("/home")
  public String viewHomePage() {
    return "home.html";
  }

}
