package arthur.deliveryapi.controller;

import arthur.deliveryapi.domain.Menu;
import arthur.deliveryapi.dto.MenuRequestDto;
import arthur.deliveryapi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService foodService) {
        this.menuService = foodService;
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void addRestaurantFood(
            @PathVariable Long restaurantId,
            @RequestBody List<MenuRequestDto> requestDtoList
    ) {
        menuService.addRestaurantFood(restaurantId, requestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Menu> findAllRestaurantFoods(
            @PathVariable Long restaurantId
    ) {
        return menuService.findAllRestaurantFoods(restaurantId);
    }
}
