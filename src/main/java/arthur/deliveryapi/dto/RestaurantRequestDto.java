package arthur.deliveryapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;

    @Builder
    public RestaurantRequestDto(String name, int minOrderPrice, int deliveryFee) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
}
