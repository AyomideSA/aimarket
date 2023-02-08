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
  private int id;
  @Column private int userid;
  @Column private LocalDate date;
  @Column private String status;

  public Order() {
  }

  public Order(int id, int userid, LocalDate date, String status) {
    this.id = id;
    this.userid = userid;
    this.date = date;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public int getUserId() {
    return userid;
  }

  public void setUserId(int userId) {
    this.userid = userId;
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
