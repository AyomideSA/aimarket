package app.aimarket.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  @Autowired
  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void save(@ModelAttribute Order order) {
    orderRepository.save(order);
  }

  public void saveAll(List<Order> orders) {
    orderRepository.saveAll(orders);
  }

  public List<Order> getOrders() {
    return orderRepository.findAll();
  }

  public List<Order> findByUserId(Long id) {
    return orderRepository.findByUserId(id);
  }

}
