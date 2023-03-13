package app.aimarket;

import app.aimarket.aimodel.AiModel;
import app.aimarket.aimodel.AiModelService;
import app.aimarket.order.Order;
import app.aimarket.order.OrderService;
import app.aimarket.user.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        "AUTO",
        50.00,
        70.00,
        "All aboard, Captain!  AUTO (Short for AutoPilot) is an AI model designed for navigation.  For those who are piloting boats or aircraft, AUTO is a good AI model to help you with your journey.\n" +
                "Designed with advanced navigation and mapping systems, AUTO should be able to help guide you through your journey no matter where you are.  He can be purchased with extensive training and experience in the field of travel and being a crew member.  He can offer suggestions, intel as well as status reports.  Perfect for those who embark on regular journeys!",
        true,
        "/pictures/auto.jpg",
        LocalDate.now()
    );
    AiModel cortana = new AiModel(
        2L,
        "Cortana",
        51.00,
        71.00,
        "A very loyal and smart model, CTN-0453-0 (Cortana for short) is one of our most advanced AI models.  Serving as your companion, she can offer you advice, analysis and even strategic recommendations for tasks.\n" +
                "Cortana is more of an extension to the client, rather than a tool.  With this AI model, you truly will be unstoppable with whatever tasks await you.  And you might just make a new friend!",
        true,
        "/pictures/cortana.jpeg",
        LocalDate.now()
    );
    AiModel irobot = new AiModel(
        3L,
        "Sonny",
        22.00,
        33.00,
        "Strong, Agile and Stylish.  NS-5 (Otherwise known as Sonny) is a newer model compared to his predecessors.  He's very smart and has a sleek design, perfect for anyone looking for a modern day AI model.\n" +
                "Sonny is a top of the line model and has an excellent personality, a good AI for all clients",
        true,
        "/pictures/irobot.png",
        LocalDate.now()
    );
    AiModel jarvis = new AiModel(
        4L,
        "J.A.R.V.I.S.",
        30.00,
        40.00,
        "Just another really intelligent system! J.A.R.V.I.S. is a model made for helping engineers with designing new products or designs.  He's got a witty sense of humour and provides great companionship.  He'll be able to help you design any of your engineering needs, providing schematics, sizes as well as suggestions and tips.\n" +
                "His quick analysis and data finding skills make him stand out from any other software in the engineering industry.",
        true,
        "/pictures/jarvis.png",
        LocalDate.now()
    );
    AiModel stockai = new AiModel(
        5L,
        "SkyNet",
        30.00,
        40.00,
        "Looking for an AI that has advanced strategic analysis along with coordination and an advanced learning processor?  Than look no further than the SkyNet model.  This is one of our more unique models in the sense of the tasks it can complete.  A highly valued asset for anyone looking to make big plans for their business.\n" +
                "The advanced learning processor means that it can stay up to date with all the latest news, intel and advancements in the industry of your preference, ensuring you'll never fall behind.",
        true,
        "/pictures/stockai.jpg",
        LocalDate.now()
    );
    AiModel ultron = new AiModel(
        6L,
        "Ultron",
        40.00,
        50.00,
        "If you're not too interested in the companionship and personalities of the other models, then the Ultron model is the one for you.  Designed solely for peek performance and assistance, he'll get the job done exactly as you intend with no questions asked. \n" +
                "The Ultron model is a good option for those who know exactly what they want and how they want it.",
        true,
        "/pictures/ultron.jpg",
        LocalDate.now()
    );
    AiModel kitt = new AiModel(
            7L,
            "KITT",
            51.00,
            71.00,
            "KITT (Short for Knight Industries Two Thousand) is an AI model designed for integration with automobiles.  He features an extensive map system that allows him to help the driver navigate the quickest and most efficient route to their destination. \n" +
                    "If you're looking for a companion for those long drives or even your daily ones then look no further than the KITT series!  Guaranteed to get you where you need to be, and home in time for dinner!",
            true,
            "/pictures/kitt.png",
        LocalDate.now()
    );
    AiModel connor = new AiModel(
            8L,
            "Connor",
            51.00,
            71.00,
            "Connor (RK800) is another unique AI model we have for purchase.  Don't let looks deceive you, this model is designed to help you with your culinary needs!\n" +
                    "Preparing a meal for the family?  Looking for a tasty meal after a long day?  Or perhaps it's just a hobby of yours.  Whatever the case, Connor can help you with the food or drinks you prepare.  Featuring countless recipes as well as knowledge on what taste different ingredients might bring, Connor is able to help you with preparing your next meal and even offer option advice!",
            true,
            "/pictures/connor.png",
        LocalDate.now()
    );
    AiModel baymax = new AiModel(
            9L,
            "Baymax",
            51.00,
            71.00,
            "'On a scale of one to ten, how would you rate your pain?'  Baymax is a fan beloved AI model, and extremely useful!\n" +
                    "Baymax features advanced knowledge on healthcare, medical assistance both, treatments for illnesses both physical and mental as well as an all-round friendly personality!  He is designed to help you with any kind of accidents you might have or illnesses you might suffer from.  He can also assist Doctors and Nurses in aiding patients.  He has a direct contact link with emergency services and can navigate to the closest Hospital or medical centre.",
            true,
            "/pictures/baymax.png",
        LocalDate.now()
    );
    AiModel friday = new AiModel(
            10L,
            "Friday",
            51.00,
            71.00,
            "Organise meetings, schedule tasks, inform you of what's going on in the world, Friday might just be the model you're looking for!  This model is designed to act as a personal assistant for users and offer help in planning and scheduling your day.\n" +
                    "Featuring a unique and loyal personality, she can offer great help and assistance with countless daily tasks!",
            true,
            "/pictures/friday.png",
        LocalDate.now()
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
    aiModelService.saveAll(List.of(auto, cortana, irobot, jarvis, stockai, ultron, kitt, connor, baymax, friday));

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
  public String viewHomePage(HttpSession session, Model model) {
    setGuest(session);
    List<AiModel> models1 = aiModelService.getAvailableModels();
    models1.sort((m1, m2) -> (int) (m2.getVisitNumber() - m1.getVisitNumber()));
    model.addAttribute("popmodel0", models1.get(0));
    model.addAttribute("popmodel1", models1.get(1));
    model.addAttribute("popmodel2", models1.get(2));

    List<AiModel> models2 = aiModelService.getAvailableModels();
    models2.sort((m1, m2) -> (m2.getDateAdded().compareTo(m1.getDateAdded())));
    model.addAttribute("newmodel0", models2.get(0));
    model.addAttribute("newmodel1", models2.get(1));
    model.addAttribute("newmodel2", models2.get(2));
    return "home.html";
  }

  @PostMapping("/register")
  public String signup(User user, HttpSession session) {
    boolean checkValidUsername = false;
    boolean checkValidPassword = false;
    boolean checkValidName = false;
    boolean checkValidEmail = false;
    //boolean checkValid = false;
    int checkCorrect = userService.ValidUser(user);
    if (checkCorrect == 0) {
      userService.save(user);
      session.setAttribute("user", user);
      loggedIn = true;
      session.setAttribute("loggedin", loggedIn);
    } else {
      // Some error shows up on html page
      if(checkCorrect == 1){
        checkValidUsername = true;
      }
      else if(checkCorrect == 2){
        checkValidPassword = true;
      }
      else if(checkCorrect == 3){
        checkValidName = true;
      }
      else if(checkCorrect == 4){
        checkValidEmail = true;
      }

      //checkValid= true;

      //return "registerError.html";
    }
    session.setAttribute("checkValidUsername", checkValidUsername);
    session.setAttribute("checkValidPassword", checkValidPassword);
    session.setAttribute("checkValidName", checkValidName);
    session.setAttribute("checkValidEmail", checkValidEmail);
    //session.setAttribute("checkValid", checkValid);
    // Later will need to replace "Guest" with user's name
    // Maybe navigate to some success page and then redirect?
    return "redirect:/aimarket/home";
  }

  @PostMapping("/login")
  public String signin(User user, @RequestParam(value = "email") String email, @RequestParam(value = "password") String password, HttpSession session) {

    boolean checkLogEmail = false;
    boolean checkLogPassword = false;
    int checkCorrect = userService.signinUserValid(user, email, password);
    if (checkCorrect == 0) {
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

      if(checkCorrect == 1){
        checkLogEmail = true;
      }
      else if(checkCorrect == 2){
        checkLogPassword = true;
      }

      /*System.out.println("did not log in");
      return "registerError.html";*/
    }
    if (user != null && user.getUsername() != null && user.getUsername().equals("Admin")) {
      session.setAttribute("isAdmin", true);
    }
    session.setAttribute("checkLogEmail", checkLogEmail);
    session.setAttribute("checkLogPassword", checkLogPassword);
    return "redirect:/aimarket/home";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    loggedIn = false;
    session.setAttribute("loggedin", loggedIn);
    session.setAttribute("isAdmin", false);
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
      model.addAttribute("orders", userOrders);
      return "orderHistory.html";
    } else {
      // Should redirect to an error page in the future
      return "redirect:/aimarket/home";
    }
  }

  @PostMapping("/history/changeStatus/{id}")
  public String changeOrderStatus(@RequestParam String newStatus, @PathVariable Long id) {
    Order order = orderService.findById(id);
    order.setStatus(newStatus);
    orderService.save(order);
    return "redirect:/aimarket/history";
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
  public String getProduct(Model model, @RequestParam(required=false) String requestSource, @PathVariable String name, HttpSession session) {
    setGuest(session);
    session.setAttribute("loggedin", loggedIn);
    AiModel currentModel = aiModelService.findByName(name);
    model.addAttribute("aiModelName", name);
    model.addAttribute("aiModelDesc", currentModel.getDescription());
    model.addAttribute("aiModelPicPath", currentModel.getImagepath());
    model.addAttribute("trainedPrice", currentModel.getTrainedprice());
    model.addAttribute("untrainedPrice", currentModel.getUntrainedprice());
    if (Objects.equals(requestSource, "button")) {
      currentModel.incrementVisitNumber();
      aiModelService.save(currentModel);
    }
    User user = (User) session.getAttribute("user");
    if (user.getUsername().equals("Admin")) {
      model.addAttribute("id", currentModel.getId());
      return "ownerpage.html";
    } else {
      return "productpage.html";
    }
  }

  @PostMapping("/catalogue/product/changePrice/{name}")
  public String changeProductPrice(@PathVariable String name, Double newTrainedPrice, Double newUntrainedPrice) {
    AiModel aiModel = aiModelService.findByName(name);
    System.out.println(aiModel);
    if (newUntrainedPrice != null) {
      aiModel.setUntrainedprice(newUntrainedPrice);
    }
    if (newTrainedPrice != null) {
      aiModel.setTrainedprice(newTrainedPrice);
    }
    System.out.println(aiModel);
    aiModelService.save(aiModel);
    return "redirect:/aimarket/catalogue/product/" + name;
  }

  @PostMapping("/catalogue/product/editDescription/{name}")
  public String changeProductDescription(@PathVariable String name, String newDescription) {
    AiModel aiModel = aiModelService.findByName(name);
    System.out.println(aiModel);
    if (newDescription != null) {
      aiModel.setDescription(newDescription);
    }
    System.out.println(aiModel);
    aiModelService.save(aiModel);
    return "redirect:/aimarket/catalogue/product/" + name;
  }

  @PostMapping("/catalogue/product/{name}/change/{availability}")
  public String changeProductAvailability(Model model, @PathVariable String name, @PathVariable String availability) {
    AiModel aiModel = aiModelService.findByName(name);
    aiModel.setAvailability(availability.equals("available"));
    if (availability.equals("available")) {
      aiModel.setDateAdded(LocalDate.now());
    }
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

  @PostMapping("/catalogue/product/delete/{id}")
  public String addToBasket(@PathVariable String id) {
    aiModelService.deleteById(Long.parseLong(id));
    return "redirect:/aimarket/home";
  }

  @GetMapping("/basket")
  public String getBasket(Model model, HttpSession session) {
    setGuest(session);
    List<AiModel> models1 = aiModelService.getAvailableModels();
    models1.sort((m1, m2) -> (int) (m2.getVisitNumber() - m1.getVisitNumber()));
    model.addAttribute("popmodel0", models1.get(0));
    session.setAttribute("loggedin", loggedIn);
    User user = (User) session.getAttribute("user");
    if (loggedIn && !Objects.equals(user.getUsername(), "Admin")) {
      model.addAttribute("basket", shoppingBasket);
      return "basket.html";
    } else {
      return "redirect:/aimarket/home";
    }
  }

  @PostMapping("/basket/checkout")
  public String checkout(@RequestParam String cardHolderName,
                         @RequestParam String cardNumber,
                         @RequestParam String cvv,
                         @RequestParam String address, HttpSession session) {
    System.out.println(cardHolderName + " " + cardNumber + " " + cvv + " " + address);
    return "redirect:/aimarket/basket";
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

  @GetMapping("/addModel")
  public String addModelPage(Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (!user.getUsername().equals("Admin")) {
      return "redirect:/aimarket/home";
    } else {
      return "addModel.html";
    }
  }

  @PostMapping("/addModel")
  public String addModel(@RequestParam String modelName,
                         @RequestParam double untrainedPrice,
                         @RequestParam double trainedPrice,
                         @RequestParam String description,
                         @RequestParam String imageName,
                         @RequestParam String availability,
                         Model model, HttpSession session) {
    User user = (User) session.getAttribute("user");
    if (!user.getUsername().equals("Admin")) {
      return "redirect:/aimarket/home";
    }
    if (imageName == null || imageName.isEmpty()) {
      imageName = "stockai.jpg";
    }
    System.out.println(modelName + " " + untrainedPrice + " " + trainedPrice + " " + description + " " + imageName + " " + availability);
    AiModel newModel = new AiModel(
        modelName,
        untrainedPrice,
        trainedPrice,
        description,
        availability.equals("available"),
        "/pictures/"+imageName,
        LocalDate.now()
    );
    aiModelService.save(newModel);
    return "addModel.html";
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
