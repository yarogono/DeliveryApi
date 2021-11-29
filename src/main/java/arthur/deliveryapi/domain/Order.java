//package arthur.deliveryapi.domain;
//
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long orderId;
//
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="restaurant_id")
//    private Restaurant restaurant;
//
//    @Builder
//    public Order(List<OrderItem> orderItems, Restaurant restaurant) {
//        this.orderItems = orderItems;
//        this.restaurant = restaurant;
//    }
//}
