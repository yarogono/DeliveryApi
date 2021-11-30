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
//    private Long id;
//
//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems = new ArrayList<>();
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="restaurant_id", nullable = false)
//    private Restaurant restaurant;
//
//    @Column(nullable = false)
//    private int totalPrice;
//
//
//
//    @Builder
//    public Order(List<OrderItem> orderItems, Restaurant restaurant) {
//        this.orderItems = orderItems;
//        this.restaurant = restaurant;
//    }
//
//
//
//
//    public void addOrderItem(OrderItem orderItem){
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
//    }
//
//    public static Order createOrder(List<OrderItem> orderItems){
//        Order order = new Order();
//        for(OrderItem orderItem: orderItems){
//            order.addOrderItem(orderItem);
//        }
//        return order;
//    }
//
//
//
//}
