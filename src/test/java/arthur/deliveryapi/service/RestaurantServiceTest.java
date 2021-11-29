package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.RestaurantRequestDto;
import arthur.deliveryapi.repository.RestaurantRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    RestaurantRepository restaurantRepository;

    @DisplayName("")
    @Test
    void addRestaurant() {
        RestaurantRequestDto requestDto = RestaurantRequestDto
                .builder()
                .name("쉐이크쉑 청담점")
                .minOrderPrice(5000)
                .deliveryFee(2000)
                .build();

        RestaurantService restaurantService = new RestaurantService(restaurantRepository);

        restaurantService.addRestaurant(requestDto);
    }
}