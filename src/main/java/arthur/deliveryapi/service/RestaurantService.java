package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.RestaurantRequestDto;
import arthur.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(RestaurantRequestDto requestDto) {
        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        checkMinOrderPrice(minOrderPrice);

        checkDeliveryFee(deliveryFee);

        Restaurant restaurant = Restaurant
                .builder()
                .name(requestDto.getName())
                .minOrderPrice(minOrderPrice)
                .deliveryFee(deliveryFee)
                .build();

        restaurantRepository.save(restaurant);

        return restaurant;
    }

    private void checkMinOrderPrice(int minOrderPrice) {
        if(!(1000 <= minOrderPrice && minOrderPrice <= 100000)) {
            throw new IllegalArgumentException("최소주문 가격 허용값을 벗어났습니다.");
        }

        if(minOrderPrice % 100 > 0) {
            throw new IllegalArgumentException("100원 단위로 입력하지 않았습니다.");
        }
    }

    private void checkDeliveryFee(int deliveryFee) {
        if(0 > deliveryFee || deliveryFee > 10_000) {
            throw new IllegalArgumentException("기본 배달비 허용값을 벗어났습니다.");
        }

        if(deliveryFee % 500 > 0) {
            throw new IllegalArgumentException("기본 배달비 500원 단위로 입력하지 않았습니다.");
        }
    }


    @Transactional
    public List<Restaurant> findAllRestaurant() {
        return restaurantRepository.findAll();
    }
}
