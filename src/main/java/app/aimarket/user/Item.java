package app.aimarket.user;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 16/02/2023, Thursday
 **/
public class Item implements Serializable {
  private String modelName;
  private String modelType;
  private double price;
  private String imagePath;

  public Item() {
  }

  public Item(String modelName, String modelType, double price, String imagePath) {
    this.modelName = modelName;
    this.modelType = modelType;
    this.price = price;
    this.imagePath = imagePath;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public String getModelType() {
    return modelType;
  }

  public void setModelType(String modelType) {
    this.modelType = modelType;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Item)) return false;
    Item item = (Item) o;
    return Double.compare(item.price, price) == 0 && modelName.equals(item.modelName) && modelType.equals(item.modelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelName, modelType, price);
  }

  @Override
  public String toString() {
    return "Item{" +
        "modelName='" + modelName + '\'' +
        ", modelType='" + modelType + '\'' +
        ", price=" + price +
        ", imagePath='" + imagePath + '\'' +
        '}';
  }
}
