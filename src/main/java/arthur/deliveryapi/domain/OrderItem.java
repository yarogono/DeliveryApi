//package arthur.deliveryapi.domain;
//
//import lombok.Builder;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@NoArgsConstructor
//public class OrderItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long orderItemId;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private int price;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id")
//    private Order order;
//
//    @Builder
//    public OrderItem(String name, int price, Order order) {
//        this.name = name;
//        this.price = price;
//        this.order = order;
//    }
//}
