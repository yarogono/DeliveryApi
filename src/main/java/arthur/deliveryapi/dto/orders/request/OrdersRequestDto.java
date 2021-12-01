package arthur.deliveryapi.dto.orders.request;

import arthur.deliveryapi.domain.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderItem> foods;
}