package arthur.deliveryapi.controller;

import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.RestaurantRequestDto;
import arthur.deliveryapi.service.RestaurantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/restaurant/register")
    public void addRestaurant(
        @RequestBody RestaurantRequestDto requestDto
    ) {
        restaurantService.addRestaurant(requestDto);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> findAllRestaurant() {
        return restaurantService.findAllRestaurant();
    }
}
