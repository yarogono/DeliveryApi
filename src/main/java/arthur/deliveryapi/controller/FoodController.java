package arthur.deliveryapi.controller;

import arthur.deliveryapi.domain.Food;
import arthur.deliveryapi.dto.FoodRequestDto;
import arthur.deliveryapi.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addRestaurantFood(
            @PathVariable Long restaurantId,
            @RequestBody List<FoodRequestDto> requestDtoList
    ) {
        foodService.addRestaurantFood(restaurantId, requestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ) {
        return foodService.findAllRestaurantFoods(restaurantId);
    }
}
