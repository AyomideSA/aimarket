package app.aimarket.user;

import app.aimarket.order.Order;

import java.util.ArrayList;
import java.util.HashMap;

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

  @Override
  public String toString() {
    return "ShoppingBasket{" +
        "basket=" + basket +
        '}';
  }
}
