package app.aimarket;

import app.aimarket.aimodel.AiModel;
import app.aimarket.aimodel.AiModelService;
import app.aimarket.order.Order;
import app.aimarket.order.OrderService;
import app.aimarket.user.User;
import app.aimarket.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path="/aimarket")
public class MarketController {
  private final UserService userService;
  private final OrderService orderService;
  private final AiModelService aiModelService;
  private final ArrayList<Order> testOrders = new ArrayList<>();
  private User user = new User("Guest");

  @Autowired
  public MarketController(UserService userService, OrderService orderService, AiModelService aiModelService) {
    this.userService = userService;
    this.orderService = orderService;
    this.aiModelService = aiModelService;
    createOrders();
  }

  // for testing
  public void createOrders() {
    testOrders.add(new Order(
        1,
        1,
        LocalDate.of(2002, Month.DECEMBER,21),
        "arrived")
    );
    testOrders.add(new Order(
        2,
        2,
        LocalDate.of(2002, Month.DECEMBER,21),
        "arrived")
    );
    testOrders.add(new Order(
        3,
        2,
        LocalDate.of(2002, Month.DECEMBER,21),
        "cancelled")
    );
    testOrders.add(new Order(
        4,
        1,
        LocalDate.of(2002, Month.DECEMBER,21),
        "cancelled")
    );
    orderService.saveAll(testOrders);
  }

  @GetMapping("/home")
  public String viewHomePage(HttpSession session) {
    setGuest(session);
    return "home.html";
  }

  @PostMapping("/register")
  public String signup(User user, HttpSession session) {
    if (userService.ValidUser(user)) {
      userService.save(user);
    } else {
      // Some error shows up on html page
    }
    // Later will need to replace "Guest" with user's name
    return "redirect:/aimarket/home";
  }

  @GetMapping("/history")
  // Need to get user's user id later
  public String getHistory(Model model, HttpSession session) {
    setGuest(session);
    user.setId(1);
    if (!Objects.equals(user.getUsername(), "Guest")) {
      List<Order> userOrders = orderService.findByUserId(user.getId());
      model.addAttribute("orders", userOrders);
      return "orderHistory.html";
    } else {
      return "redirect:/aimarket/home";
    }
  }

  @GetMapping("/catalogue")
  public String getCatalogue(Model model, HttpSession session) {
    setGuest(session);
    List<AiModel> models = aiModelService.getAvailableModels();
    model.addAttribute("aimodels", models);
    return "catalogue.html";
  }

  @GetMapping("/catalogue/product/{name}")
  public String getProduct(Model model, @PathVariable String name, HttpSession session) {
    setGuest(session);
    AiModel currentModel = aiModelService.findByName(name);
    model.addAttribute("aiModelName", name);
    model.addAttribute("aiModelDesc", currentModel.getDescription());
    model.addAttribute("aiModelPicPath", currentModel.getImagepath());
    model.addAttribute("trainedPrice", currentModel.getTrainedprice());
    model.addAttribute("untrainedPrice", currentModel.getUntrainedprice());
    return "productpage.html";
  }

  private void setGuest(HttpSession session) {
    if (session.getAttribute("user") == null) {
      session.setAttribute("user", user);
      session.setAttribute("greeting", user.getUsername() + ", sign in");
    }
  }

}
