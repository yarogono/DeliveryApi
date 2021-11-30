//package arthur.deliveryapi.service;
//
//import arthur.deliveryapi.domain.Food;
//import arthur.deliveryapi.domain.Order;
//import arthur.deliveryapi.domain.OrderItem;
//import arthur.deliveryapi.domain.Restaurant;
//import arthur.deliveryapi.dto.OrderRequestDto;
//import arthur.deliveryapi.repository.FoodRepository;
//import arthur.deliveryapi.repository.OrderRepository;
//import arthur.deliveryapi.repository.RestaurantRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Service
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final RestaurantRepository restaurantRepository;
//    private final FoodRepository foodRepository;
//
//    @Autowired
//    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository) {
//        this.orderRepository = orderRepository;
//        this.restaurantRepository = restaurantRepository;
//        this.foodRepository = foodRepository;
//    }
//
//    /*
//
//{
//  restaurantName: "쉐이크쉑 청담점",
//  foods: [
//    {
//      name: "쉑버거 더블",
//      quantity: 1,
//      price: 10900
//    },
//    {
//      name: "치즈 감자튀김",
//      quantity: 2,
//      price: 9800
//    },
//    {
//      name: "쉐이크",
//      quantity: 3,
//      price: 17700
//    }
//  ],
//  deliveryFee: 2000,
//  totalPrice: 40400
//}
//     */
//
//
//
//    public Order order(OrderRequestDto requestDto) {
//        Restaurant restaurant = restaurantRepository.findById(requestDto.getRestaurantId())
//                .orElseThrow(
//                        () -> new NullPointerException("해당 음식점이 없습니다.")
//                );
//
//
//        List<OrderItem> resultItems = new ArrayList<>();
//        Order order =  new Order();
//
//        List<OrderItem> orderItems = requestDto.getFoods();
//        for (OrderItem tempOrderItem : orderItems) {
//            Food food = foodRepository.findById(tempOrderItem.getId())
//                    .orElseThrow(() -> new NullPointerException("해당 음식이 없습니다."));
//
//            OrderItem orderItem = OrderItem.builder()
//                    .quantity(tempOrderItem.getQuantity())
//                    .order(order)
//                    .food(food)
//                    .build();
//            resultItems.add(orderItem);
//        }
//        order = new Order(resultItems, restaurant);
//        return orderRepository.save(order);
//    }
//
//    public List<Order> findAllOrder() {
//        return orderRepository.findAll();
//    }
//}
