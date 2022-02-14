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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static arthur.deliveryapi.exception.ExceptionMessages.*;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrdersResponseDto order(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = getRestaurant(ordersRequestDto);

        int totalPrice = 0;
        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItems = ordersRequestDto.getFoods();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItem tempOrderItem : orderItems) {

            int quantity = tempOrderItem.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException(ILLEGAL_FOOD_ORDER_QUANTITY);
            }

            Food food = getFood(tempOrderItem);

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
            throw new IllegalArgumentException(ILLEGAL_TOTAL_PRICE);
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        Orders orders = new Orders(restaurant.getName(), totalPrice, orderItemList);
        orderRepository.save(orders);
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
        return ordersResponseDto;
    }


    private Restaurant getRestaurant(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException(RESTAURANT_IS_NULL)
                );
        return restaurant;
    }

    private Food getFood(OrderItem tempOrderItem) {
        return foodRepository.findById(tempOrderItem.getId())
                .orElseThrow(() -> new NullPointerException(CANT_FIND_FOOD));
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
