//package arthur.deliveryapi.controller;
//
//import arthur.deliveryapi.domain.Order;
//import arthur.deliveryapi.dto.OrderRequestDto;
//import arthur.deliveryapi.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class OrderController {
//
//    private final OrderService orderService;
//
//    @Autowired
//    public OrderController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/order/request")
//    public Order orderFood(
//            @RequestBody OrderRequestDto requestDto
//    ) {
//        return orderService.order(requestDto);
//    }
//
//    @GetMapping("/orders")
//    public List<Order> findAllOrder() {
//        return orderService.findAllOrder();
//    }
//}
