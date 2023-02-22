package app.aimarket;

import app.aimarket.aimodel.AiModel;
import app.aimarket.aimodel.AiModelService;
import app.aimarket.order.Order;
import app.aimarket.order.OrderService;
import app.aimarket.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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
        5L,
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
        4L,
        LocalDate.now(),        "cancelled",
        "Ultron_trained_72.21_2-Auto_untrained_21.21_5"
    ));
    orderService.saveAll(testOrders);
    aiModelService.saveAll(List.of(auto, cortana, irobot, jarvis, stockai, ultron));

    User admin = new User("Admin", "adminpass", "Admin", "admin@admin.com");
    userService.save(admin);
  }

  /*
  Features such as the shopping basking, order history and the buying of products
  Will not be allowed for guests in the future. They may only be allowed now for
  the purpose of testing until the login feature is finished.

  In the feature, trying to access these features as a guest should map the user
  to a register page/popup.
   */

  // Used for faster login for testing
  public void autoAdminLogin(HttpSession session) {
    User user = new User("Admin", "Admin");
    loggedIn = true;
    session.setAttribute("user", user);
    session.setAttribute("loggedin", loggedIn);
  }

  public void setUserTest(HttpSession session) {
    User user = new User("sdddf4", "Ayo");
    loggedIn = true;
    session.setAttribute("user", user);
    session.setAttribute("loggedin", loggedIn);
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
      session.setAttribute("user", user);
      loggedIn = true;
      session.setAttribute("loggedin", loggedIn);
    } else {
      // Some error shows up on html page
      return "registerError.html";
    }
    // Later will need to replace "Guest" with user's name
    // Maybe navigate to some success page and then redirect?
    return "redirect:/aimarket/home";
  }

  @PostMapping("/login")
  public String signin(User user, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session) {
    System.out.println(email+password);
    if (userService.signinUserValid(user, email, password)) {
      System.out.println("Worked");
      //User user = userService.findUserByEmail(email);
      user = userService.findUserByEmail(email);
      System.out.println(user);
      session.setAttribute("user", user);
      loggedIn = true;
      session.setAttribute("loggedin", loggedIn);
    } else {
      // Some error shows up on html page
      loggedIn = false;
      System.out.println("did not log in");
      return "registerError.html";
    }
    return "redirect:/aimarket/home";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    loggedIn = false;
    session.setAttribute("loggedin", loggedIn);
    shoppingBasket.clear();
    return "redirect:/aimarket/home";
  }

  @GetMapping("/history")
  public String getHistory(Model model, HttpSession session) {
    boolean adminLoggedIn=false;
    setGuest(session);
    session.setAttribute("loggedin", loggedIn);
    if (loggedIn) {
      User user = (User) session.getAttribute("user");
      List<Order> userOrders;
      if (Objects.equals(user.getUsername(), "Admin")) {
        adminLoggedIn=true;
        userOrders=orderService.getOrders();
      } else {
        userOrders = orderService.findByUserId(user.getId());
      }
      model.addAttribute("isAdmin", adminLoggedIn);
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
    session.setAttribute("loggedin", loggedIn);
    List<AiModel> models;
    User user = (User) session.getAttribute("user");
    if (user.getUsername().equals("Admin")) {
      models = aiModelService.getAllModels();
    } else {
      models = aiModelService.getAvailableModels();
    }
    model.addAttribute("aimodels", models);
    return "catalogue.html";
  }

  @GetMapping("/catalogue/product/{name}")
  public String getProduct(Model model, @PathVariable String name, HttpSession session) {
    setGuest(session);
    session.setAttribute("loggedin", loggedIn);
    AiModel currentModel = aiModelService.findByName(name);
    model.addAttribute("aiModelName", name);
    model.addAttribute("aiModelDesc", currentModel.getDescription());
    model.addAttribute("aiModelPicPath", currentModel.getImagepath());
    model.addAttribute("trainedPrice", currentModel.getTrainedprice());
    model.addAttribute("untrainedPrice", currentModel.getUntrainedprice());
    User user = (User) session.getAttribute("user");
    if (user.getUsername().equals("Admin")) {
      return "ownerpage.html";
    } else {
      return "productpage.html";
    }
  }

  @PostMapping("/catalogue/product/{name}/{availability}")
  public String changeProductAvailability(Model model, @PathVariable String name, @PathVariable String availability) {
    AiModel aiModel = aiModelService.findByName(name);
    aiModel.setAvailability(availability.equals("available"));
    aiModelService.save(aiModel);
    return "redirect:/aimarket/catalogue/product/" + name;
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
    session.setAttribute("loggedin", loggedIn);
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
      session.setAttribute("user", new User("Guest"));
      session.setAttribute("loggedin", loggedIn);
    }
  }

  @PostMapping("/error")
  public String returnError(){
    return "redirect:/aimarket/home";
  }

}
