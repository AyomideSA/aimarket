package app.aimarket.order;

import app.aimarket.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author : ayoso
 * @mailto : ayomide.sola-ayodele@ucdconnect.ie
 * @since : 07/02/2023, Tuesday
 **/
public interface OrderRepository extends
    JpaRepository<Order, Long> {

  @Query("select o from Order o where o.userid = ?#{[0]}")
  List<Order> findByUserId(int id);
}
