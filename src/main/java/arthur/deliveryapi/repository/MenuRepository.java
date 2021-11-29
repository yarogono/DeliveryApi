package arthur.deliveryapi.repository;

import arthur.deliveryapi.domain.Menu;
import arthur.deliveryapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
     List<Menu> findFoodsByRestaurant(Restaurant restaurant);
}
