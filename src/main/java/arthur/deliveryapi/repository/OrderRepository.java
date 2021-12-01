package arthur.deliveryapi.repository;

import arthur.deliveryapi.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}
