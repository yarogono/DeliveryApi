package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Menu;
import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.MenuRequestDto;
import arthur.deliveryapi.repository.MenuRepository;
import arthur.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository, RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurantFood(Long restaurantId, List<MenuRequestDto> requestDtoList) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow();
        List<Menu> foods = menuRepository.findFoodsByRestaurant(restaurant);
        String restaurantName = restaurant.getName();

        for (MenuRequestDto requestDto: requestDtoList) {

            if (foods.contains(requestDto.getName())) {
                throw new IllegalArgumentException(restaurantName + " 음식점에 이미 등록된 음식입니다.");
            }

            int foodPrice = requestDto.getPrice();
            System.out.println(foodPrice);
            if (!(100 <=foodPrice && foodPrice <= 1_000_000)) {
                throw new IllegalArgumentException("음식 가격 허용범위를 벗어났습니다.");
            }

            if (foodPrice % 100 > 0) {
                throw new IllegalArgumentException("음식 가격이 100원 단위가 아닙니다.");
            }
        }

        // 해당 레스토랑에 같은 음식이 있는지 확인


    }

    public List<Menu> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("해당 레스토랑이 없습니다."));

        return menuRepository.findFoodsByRestaurant(restaurant);
    }
}
