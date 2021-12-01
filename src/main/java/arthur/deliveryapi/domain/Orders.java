package arthur.deliveryapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "orders_id")
    private List<OrderItem> foods;


    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;


    @Builder
    public Orders(String restaurantName, int totalPrice, List<OrderItem> orderItems) {
        this.restaurantName = restaurantName;
        this.totalPrice = totalPrice;
        this.foods = orderItems;
    }
}
