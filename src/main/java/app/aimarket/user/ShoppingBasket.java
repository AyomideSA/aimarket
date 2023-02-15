package app.aimarket.user;

import app.aimarket.order.Order;
import org.aspectj.weaver.ast.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 14/02/2023, Tuesday
 **/
public class ShoppingBasket {
  private HashMap<Order, Integer> basket = new HashMap<>();

  public void add(Order order) {
    System.out.println(order);
    basket.putIfAbsent(order, 0);
    basket.put(order, basket.get(order)+1);
  }

  public HashMap<Order, Integer> getBasket() {
    return basket;
  }

  public void setBasket(HashMap<Order, Integer> basket) {
    this.basket = basket;
  }

  public void remove(Order order) {
    basket.remove(order);
  }

  public void setQuantity(Order order, int quantity) {
    basket.put(order, quantity);
  }

  public void setType(Order oldItem, String newType, double newPrice) {
    if (!Objects.equals(oldItem.getModelType(), newType)) {
      String modelName = oldItem.getModelName();
      String imagePath = oldItem.getImagePath();
      Order newItem = new Order(modelName, newType, newPrice, imagePath);
      int oldItemQty = basket.get(oldItem);
      System.out.println(newItem);
      System.out.println(basket);
      if (basket.containsKey(newItem)) {
        basket.put(newItem, basket.get(newItem)+oldItemQty);
      } else {
        basket.put(newItem, oldItemQty);
      }
      basket.remove(oldItem);
    }
  }

  @Override
  public String toString() {
    return "ShoppingBasket{" +
        "basket=" + basket +
        '}';
  }

}
