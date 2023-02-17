package app.aimarket;

import app.aimarket.aimodel.AiModel;
import app.aimarket.aimodel.AiModelService;
import app.aimarket.order.Order;
import app.aimarket.order.OrderService;
import app.aimarket.user.Item;
import app.aimarket.user.ShoppingBasket;
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
  private final ShoppingBasket shoppingBasket = new ShoppingBasket();
  private boolean loggedIn = false;

  @Autowired
  public MarketController(UserService userService, OrderService orderService, AiModelService aiModelService) {
    this.userService = userService;
    this.orderService = orderService;
    this.aiModelService = aiModelService;
    createTestData();
  }

  // for testing
  public void createTestData() {
    AiModel auto = new AiModel(
        1L,
        "Auto",
        50.00,
        70.00,
        "A pretty good ai",
        true,
        "/pictures/auto.jpg"
    );
    AiModel cortana = new AiModel(
        2L,
        "cortana",
        51.00,
        71.00,
        "A really good ai",
        true,
        "/pictures/cortana.jpeg"
    );
    AiModel irobot = new AiModel(
        3L,
        "irobot",
        22.00,
        33.00,
        "A really good ai model",
        true,
        "/pictures/irobot.png"
    );
    AiModel jarvis = new AiModel(
        4L,
        "jarvis",
        30.00,
        40.00,
        "The Best AI model",
        true,
        "/pictures/jarvis.png"
    );
    AiModel stockai = new AiModel(
        5L,
        "stockai",
        30.00,
        40.00,
        "The Best AI stock",
        true,
        "/pictures/stockai.jpg"
    );
    AiModel ultron = new AiModel(
        6L,
        "ultron",
        40.00,
        50.00,
        "ULTRON",
        true,
        "/pictures/ultron.jpg"
    );

    testOrders.add(new Order(
        1L,
        LocalDate.now(),
        "arrived",
        "Ultron_trained_72.21_2-Auto_untrained_21.21_5"
    ));
    testOrders.add(new Order(
        2L,
        LocalDate.now(),        "arrived",
        "Ultron_trained_72.21_2-Auto_untrained_21.21_5"
    ));

    testOrders.add(new Order(
        2L,
        LocalDate.now(),        "cancelled",
        "Ultron_trained_72.21_2-Auto_untrained_21.21_5"
    ));
    testOrders.add(new Order(
        1L,
        LocalDate.now(),        "cancelled",
        "Ultron_trained_72.21_2-Auto_untrained_21.21_5"
    ));
    orderService.saveAll(testOrders);
    aiModelService.saveAll(List.of(auto, cortana, irobot, jarvis, stockai, ultron));
  }

  /*
  Features such as the shopping basking, order history and the buying of products
  Will not be allowed for guests in the future. They may only be allowed now for
  the purpose of testing until the login feature is finished.

  In the feature, trying to access these features as a guest should map the user
  to a register page/popup.
   */

  @GetMapping("/home")
  public String viewHomePage(HttpSession session) {
    setGuest(session);
    return "home.html";
  }

  @PostMapping("/register")
  public String signup(User user, HttpSession session) {
    if (userService.ValidUser(user)) {
      userService.save(user);
      this.user = user;
      session.setAttribute("user", user);
      loggedIn = true;
    } else {
      // Some error shows up on html page
      return "registerError.html";
    }
    // Later will need to replace "Guest" with user's name
    // Maybe navigate to some success page and then redirect?
    return "redirect:/aimarket/home";
  }

  @PostMapping("/login")
  public String signin(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session) {
    System.out.println(email+password);
    if (true) {
      System.out.println("Worked");
      User user = userService.findUserByEmail(email);
      this.user = user;
      session.setAttribute("user", user);
      loggedIn = true;
    } else {
      // Some error shows up on html page
      loggedIn = false;
      System.out.println("did not log in");
    }
    return "redirect:/aimarket/home";
  }

  @GetMapping("/history")
  public String getHistory(Model model, HttpSession session) {
    setGuest(session);
    if (loggedIn) {
      List<Order> userOrders = orderService.findByUserId(user.getId());
      model.addAttribute("orders", userOrders);
      return "orderHistory.html";
    } else {
      // Should redirect to an error page in the future
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

  @PostMapping("/catalogue/product/{name}/{type}/add")
  public String addToBasket(@PathVariable String name, @PathVariable String type, double price, HttpSession session) {
    setGuest(session);
    if (loggedIn) {
      AiModel aiModel = aiModelService.findByName(name);
      double modelPrice = Objects.equals(type, "trained") ?
          aiModel.getTrainedprice() : aiModel.getUntrainedprice();
      shoppingBasket.add(new Item(name, type, price, aiModel.getImagepath()));
      System.out.println(shoppingBasket);
      return "redirect:/aimarket/catalogue";
    } else {
      return "redirect:/aimarket/home";
    }
  }

  @GetMapping("/basket")
  public String getBasket(Model model, HttpSession session) {
    setGuest(session);
    if (loggedIn) {
      model.addAttribute("basket", shoppingBasket);
      return "basket.html";
    } else {
      return "redirect:/aimarket/home";
    }
  }

  @PostMapping("/basket/delete/{modelName}/{modelType}/{price}")
  public String deleteItem(Item item) {
    shoppingBasket.remove(item);
    return "redirect:/aimarket/basket";
  }

  @PostMapping("/basket/changeqty/{modelName}/{modelType}/{price}")
  public String changeQuantity(@RequestParam int newQuantity,
                               @PathVariable String modelName,
                               @PathVariable String modelType,
                               @PathVariable double price) {
    String imagePath = aiModelService.findByName(modelName).getImagepath();
    shoppingBasket.setQuantity(new Item(modelName, modelType, price, imagePath), newQuantity);
    return "redirect:/aimarket/basket";
  }

  @PostMapping("/basket/changetype/{modelName}/{modelType}/{price}")
  public String changeType(@RequestParam String newType,
                           @PathVariable String modelName,
                           @PathVariable String modelType,
                           @PathVariable double price) {
    AiModel aiModel = aiModelService.findByName(modelName);
    String imagePath = aiModel.getImagepath();
    double newPrice = Objects.equals(newType, "trained") ? aiModel.getTrainedprice() : aiModel.getUntrainedprice();
    shoppingBasket.setType(new Item(modelName, modelType, price, imagePath), newType, newPrice);
    return "redirect:/aimarket/basket";
  }

  // Sets user to guest for the first page that a non-logged-in user visits
  private void setGuest(HttpSession session) {
    if (!loggedIn) {
      session.setAttribute("user", user);
    }
  }

  @PostMapping("/error")
  public String returnError(){
    return "redirect:/aimarket/home";
  }

}
