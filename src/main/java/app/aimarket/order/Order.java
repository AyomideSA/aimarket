package app.aimarket.order;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
  @Size(max = 1400)
  @Column private String orderDetails;

  public Order() {
  }

  public Order(Long userid, LocalDate date, String status, String orderDetails) {
    this.userid = userid;
    this.date = date;
    this.status = status;
    this.orderDetails = orderDetails;
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

  public String getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(String orderDetails) {
    this.orderDetails = orderDetails;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", userid=" + userid +
        ", date=" + date +
        ", status='" + status + '\'' +
        '}';
  }
}
