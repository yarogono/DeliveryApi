package arthur.deliveryapi.service;

import arthur.deliveryapi.domain.Food;
import arthur.deliveryapi.domain.OrderItem;
import arthur.deliveryapi.domain.Orders;
import arthur.deliveryapi.domain.Restaurant;
import arthur.deliveryapi.dto.orders.request.OrdersRequestDto;
import arthur.deliveryapi.dto.orders.response.FoodsResponseDto;
import arthur.deliveryapi.dto.orders.response.OrdersResponseDto;
import arthur.deliveryapi.repository.FoodRepository;
import arthur.deliveryapi.repository.OrderItemRepository;
import arthur.deliveryapi.repository.OrderRepository;
import arthur.deliveryapi.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderItemRepository = orderItemRepository;
    }


    @Transactional
    public OrdersResponseDto order(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException("해당 음식점이 없습니다.")
                );
        int totalPrice = 0;
        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItems = ordersRequestDto.getFoods();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItem tempOrderItem : orderItems) {

            int quantity = tempOrderItem.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("음식 주문 수량은 1 ~ 100미만으로 입니다.");
            }

            Food food = foodRepository.findById(tempOrderItem.getId())
                    .orElseThrow(() -> new NullPointerException("해당 음식이 없습니다."));

            OrderItem orderItem = OrderItem.builder()
                    .quantity(tempOrderItem.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderItemRepository.save(orderItem);
            FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
            foodsResponseDtoList.add(foodsResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderItemList.add(orderItem);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("음식점의 최소 주문 가격을 넘지 않았습니다.");
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        Orders orders = new Orders(restaurant.getName(), totalPrice, orderItemList);
        orderRepository.save(orders);
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
        return ordersResponseDto;
    }

    @Transactional
    public List<OrdersResponseDto> findAllOrder() {
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = orderRepository.findAll();

        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();


            List<OrderItem> orderItemsList  = orderItemRepository.findOrderItemsByOrders(orders);
            for (OrderItem orderItem : orderItemsList) {
               FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
               foodsResponseDtoList.add(foodsResponseDto);
            }

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
            ordersResponseDtoList.add(ordersResponseDto);
        }

        return ordersResponseDtoList;
    }
}
