package app.aimarket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MarketController {
  private final UserService userService;
  private final OrderService orderService;
  // Testing
  private final ArrayList<Order> testOrders = new ArrayList<>();

  @Autowired
  public MarketController(UserService userService, OrderService orderService) {
    this.userService = userService;
    this.orderService = orderService;
  }

  // for testing
  public void createOrders() {
    testOrders.add(new Order(
        1,
        5,
        LocalDate.of(2002, Month.DECEMBER,21),
        "arrived")
    );
    testOrders.add(new Order(
        2,
        5,
        LocalDate.of(2002, Month.DECEMBER,21),
        "arrived")
    );
    testOrders.add(new Order(
        3,
        5,
        LocalDate.of(2002, Month.DECEMBER,21),
        "cancelled")
    );
    testOrders.add(new Order(
        4,
        3,
        LocalDate.of(2002, Month.DECEMBER,21),
        "cancelled")
    );
    orderService.saveAll(testOrders);
  }

  @GetMapping("/aimarket/home")
  public String viewHomePage(Model model) {
    createOrders();
    model.addAttribute("name", "Guest, sign in");
    return "home.html";
  }

  @PostMapping("aimarket/register")
  public String signup(@ModelAttribute User user, Model model) {
    if (userService.ValidUser(user)) {
      model.addAttribute("user", user);
      userService.save(user);
    } else {
      // Some error shows up on html page
    }
    // Later will need to replace "Guest" with user's name
    return "redirect:/aimarket/home";
  }

  @GetMapping("aimarket/history")
  // Need to get user's user id later
  public String getHistory(Model model, Integer userid) {
   List<Order> userOrders = orderService.findByUserId(5);
   model.addAttribute("orders", userOrders);
    return "orderHistory.html";
  }

  @GetMapping("aimarket/catalogue")
  public String getCatalogue() {
    return "catalogue.html";
  }

}
