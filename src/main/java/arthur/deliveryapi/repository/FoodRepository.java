package arthur.deliveryapi.repository;

import arthur.deliveryapi.domain.Food;
import arthur.deliveryapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
     List<Food> findFoodsByRestaurant(Restaurant restaurant);
     Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}
