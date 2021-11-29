package arthur.deliveryapi.testdata;


import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.repository.RestaurantRepository;
import arthur.deliveryapi.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataRunner implements ApplicationRunner {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantService restaurantService;

    public void run(ApplicationArguments args) throws Exception {
        // 테스트 음식점 생성
        Restaurant testRestaurant = Restaurant.builder()
                .name("쉐이크쉑 청담점")
                .minOrderPrice(5000)
                .deliveryFee(2000)
                .build();

        restaurantRepository.save(testRestaurant);

    }

}
