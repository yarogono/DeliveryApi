package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Food;
import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.FoodRequestDto;
import arthur.deliveryapi.repository.FoodRepository;
import arthur.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void addRestaurantFoods(
            Long restaurantId,
            List<FoodRequestDto> requestDtoList
    ) {
        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);

        if (!foundRestaurant.isPresent()) {
            throw new IllegalArgumentException("해당 음식점이 없습니다.");
        }
        Restaurant restaurant = foundRestaurant.get();

        for (FoodRequestDto requestDto : requestDtoList) {

            int price = requestDto.getPrice();
            if (price < 100) {
                throw new IllegalArgumentException("음식 가격이 100원 미만입니다.");
            }

            if (price > 1_000_000) {
                throw new IllegalArgumentException("음식 가격이 1,000,000원을 초과했습니다.");
            }

            if (price % 100 > 0) {
                throw new IllegalArgumentException("음식 가격이 100원 단위로 입력되었습니다.");
            }

            Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, requestDto.getName());
            if(found.isPresent()){
                throw new IllegalArgumentException("중복된 이름의 음식이 존재합니다.");
            }


            Food food = Food.builder()
                    .name(requestDto.getName())
                    .price(requestDto.getPrice())
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }
    }

    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("해당 레스토랑이 없습니다."));

        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}
