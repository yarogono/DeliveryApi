package arthur.deliveryapi.dto;

import arthur.deliveryapi.domain.Menu;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<Menu> foods;
}
