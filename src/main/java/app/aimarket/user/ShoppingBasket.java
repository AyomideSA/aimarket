package app.aimarket.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 14/02/2023, Tuesday
 **/
public class ShoppingBasket {
  private int test = 0;
  private HashMap<Item, Integer> basket = new HashMap<>();

  public void add(Item item) {
    basket.putIfAbsent(item, 0);
    basket.put(item, basket.get(item) + 1);
  }

  public HashMap<Item, Integer> getBasket() {
    return basket;
  }

  public int getTest() {
    return test;
  }

  public void setTest(int test) {
    this.test = test;
  }

  public void setBasket(HashMap<Item, Integer> basket) {
    this.basket = basket;
  }

  public void remove(Item item) {
    basket.remove(item);
  }

  public void setQuantity(Item item, int quantity) {
    basket.put(item, quantity);
  }

  public void setType(Item oldItem, String newType, double newPrice) {
    if (!Objects.equals(oldItem.getModelType(), newType)) {
      String modelName = oldItem.getModelName();
      String imagePath = oldItem.getImagePath();
      Item newItem = new Item(modelName, newType, newPrice, imagePath);
      int oldItemQty = basket.get(oldItem);
      if (basket.containsKey(newItem)) {
        basket.put(newItem, basket.get(newItem) + oldItemQty);
      } else {
        basket.put(newItem, oldItemQty);
      }
      basket.remove(oldItem);
    }
  }

  public int totalItems() {
    int total = 0;
    for (Integer qty : basket.values()) {
      total += qty;
    }
    return total;
  }

  public double totalPrice() {
    double total = 0;
    for (Item item : basket.keySet()) {
      total += item.getPrice() * basket.get(item);
    }
    return total;
  }

  public void clear() {
    basket.clear();
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    for (Map.Entry<Item, Integer> entry : basket.entrySet()) {
      s.append(entry.getKey().toString()).append('_').append(entry.getValue()).append('-');
    }
    return s.substring(0, s.length() - 1);
  }

}
