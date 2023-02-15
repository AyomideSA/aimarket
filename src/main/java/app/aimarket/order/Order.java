package app.aimarket.order;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 07/02/2023, Tuesday
 **/
@Entity
@Table(name="orders")
public class Order implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column private Long userid;
  @Column private LocalDate date;
  @Column private String status;
  @Column private String modelName;
  @Column private String modelType;
  @Column private double price;
  @Column private String imagePath;

  public Order() {
  }

  public Order(Long id, Long userid, LocalDate date, String status, String modelName, String modelType, double price, String imagePath) {
    this.id = id;
    this.userid = userid;
    this.date = date;
    this.status = status;
    this.modelName = modelName;
    this.modelType = modelType;
    this.price = price;
    this.imagePath = imagePath;
  }

  public Order(Long userid, LocalDate date, String status, String modelName, String modelType, double price, String imagePath) {
    this.userid = userid;
    this.date = date;
    this.status = status;
    this.modelName = modelName;
    this.modelType = modelType;
    this.price = price;
    this.imagePath = imagePath;
  }



  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getUserId() {
    return userid;
  }

  public void setUserId(Long userId) {
    this.userid = userId;
  }

  public String getModelName() {
    return modelName;
  }

  public void setModelName(String modelName) {
    this.modelName = modelName;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getModelType() {
    return modelType;
  }

  public void setModelType(String modelType) {
    this.modelType = modelType;
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
    if (!(o instanceof Order)) return false;
    Order order = (Order) o;
    return modelName.equals(order.modelName) && modelType.equals(order.modelType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelName, modelType);
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", userid=" + userid +
        ", date=" + date +
        ", status='" + status + '\'' +
        ", modelName='" + modelName + '\'' +
        ", modelType='" + modelType + '\'' +
        ", price=" + price +
        ", imagePath='" + imagePath + '\'' +
        '}';
  }
}
