package app.aimarket.order;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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

  public Order() {
  }

  public Order(Long id, Long userid, LocalDate date, String status,
               String modelName, String modelType, double price) {
    this.id = id;
    this.userid = userid;
    this.date = date;
    this.status = status;
    this.modelName = modelName;
    this.price = price;
    this.modelType = modelType;
  }

  public Order(Long userid, LocalDate date, String status, String modelName, String modelType, double price) {
    this.userid = userid;
    this.date = date;
    this.status = status;
    this.modelName = modelName;
    this.modelType = modelType;
    this.price = price;
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
        '}';
  }
}
