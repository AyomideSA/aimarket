package app.aimarket.user;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 14/02/2023, Tuesday
 **/
public class ShoppingBasket {
  private HashMap<Item, Integer> basket = new HashMap<>();

  public void add(Item item) {
    basket.putIfAbsent(item, 0);
    basket.put(item, basket.get(item)+1);
  }

  public HashMap<Item, Integer> getBasket() {
    return basket;
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
