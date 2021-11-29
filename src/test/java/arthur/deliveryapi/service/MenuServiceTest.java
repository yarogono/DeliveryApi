package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.MenuRequestDto;
import arthur.deliveryapi.dto.RestaurantRequestDto;
import arthur.deliveryapi.repository.MenuRepository;
import arthur.deliveryapi.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @Mock
    MenuRepository menuRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    private Long restaurantId = 1L;

    @Test
    void addRestaurantFood() {
        RestaurantRequestDto restaurantRequestDto = RestaurantRequestDto
                .builder()
                .name("쉐이크쉑 청담점")
                .minOrderPrice(5000)
                .deliveryFee(2000)
                .build();

        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequestDto.getName())
                .minOrderPrice(restaurantRequestDto.getMinOrderPrice())
                .deliveryFee(restaurantRequestDto.getDeliveryFee())
                .build();
//        RestaurantService restaurantService = new RestaurantService(restaurantRepository);
//        restaurantService.addRestaurant(restaurantRequestDto);
        when(restaurantRepository.save(restaurant));

        MenuService menuService = new MenuService(menuRepository, restaurantRepository);

        MenuRequestDto menuRequestDto = new MenuRequestDto(
                "쉑버거 더블",
                10900
        );
//        menuService.addRestaurantFood(restaurantId, menuRequestDto);
    }

    @Test
    void findAllRestaurantFoods() {
    }
}