//package arthur.deliveryapi.domain;
//
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(nullable = false)
//    private int quantity;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "food_id")
//    private Food Food;
//
//    @Builder
//    public OrderItem(int quantity, Order order, arthur.deliveryapi.domain.Food food) {
//        this.quantity = quantity;
//        this.order = order;
//        Food = food;
//    }
//
//    //    public static OrderItem createOrderItem(Food food, int price){
////        OrderItem orderItem = new OrderItem();
////        orderItem.setFood(food);
////        orderItem.setPrice(price);
////        return orderItem;
////    }
//
////    public int getTotalPrice() {
////        return this.getPrice() * this.getCount();
////    }
//
//
////    @Builder
////    public OrderItem(String name, int price, Order order) {
////        this.name = name;
////        this.price = price;
////        this.order = order;
////    }
//}
