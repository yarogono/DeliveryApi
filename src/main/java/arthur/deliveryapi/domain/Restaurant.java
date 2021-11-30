package arthur.deliveryapi.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;


    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Food> foods = new ArrayList<>();

    //    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
//    private List<Order> orders = new ArrayList<>();



    @Builder
    public Restaurant(
            String name,
            int minOrderPrice,
            int deliveryFee
    ) {
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }

//    public void addFood(List<Food> foods) {
//        this.foods.addAll(foods);
//    }
}
