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

  @GetMapping("/home")
  public String viewHomePage(HttpSession session) {
    createOrders();
    if (session.getAttribute("user") == null) {
      session.setAttribute("user", user);
      session.setAttribute("greeting", user.getUsername() + ", sign in");
    }
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
  public String getHistory(Model model, Integer userid) {
   List<Order> userOrders = orderService.findByUserId(5);
   model.addAttribute("orders", userOrders);
    return "orderHistory.html";
  }

  @GetMapping("/catalogue")
  public String getCatalogue(Model model) {
   // System.out.println(aiModelService.getAvailabileModels().get(0).getImageurl());
    model.addAttribute("url", aiModelService.getAvailableModels().get(0).getImagepath());
    List<AiModel> models = aiModelService.getAvailableModels();
    System.out.println(models);
    model.addAttribute("aimodels", models);
    return "catalogue.html";
  }

  @GetMapping("/catalogue/product/{name}")
  public String getProduct(Model model, @PathVariable String name) {
    AiModel currentModel = aiModelService.findByName(name);
    model.addAttribute("aiModelName", name);
    model.addAttribute("aiModelDesc", currentModel.getDescription());
    model.addAttribute("aiModelPicPath", currentModel.getImagepath());
    model.addAttribute("trainedPrice", currentModel.getTrainedprice());
    model.addAttribute("untrainedPrice", currentModel.getUntrainedprice());
    return "productpage.html";
  }

}
