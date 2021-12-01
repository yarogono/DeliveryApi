package arthur.deliveryapi.repository;

import arthur.deliveryapi.domain.OrderItem;
import arthur.deliveryapi.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findOrderItemsByOrders(Orders orders);
}
