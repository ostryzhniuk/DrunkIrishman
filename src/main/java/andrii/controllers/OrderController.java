package andrii.controllers;

import andrii.dto.OrderContentDTO;
import andrii.dto.OrderDTO;
import andrii.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order/create")
    public void createOrder(@RequestBody OrderDTO orderDTO) {
        orderService.saveOrder(orderDTO);
    }

    @GetMapping("/orders")
    public List<OrderDTO> orders(@RequestParam(value= "status") OrderDTO.Status status){
        return  orderService.getOrders(status);
    }

    @GetMapping("/order/status/list")
    public OrderDTO.Status[] orderStatusList(){
        return  OrderDTO.Status.values();
    }

    @PutMapping("/order/status/change")
    public void changeOrderStatus(@RequestBody OrderDTO orderDTO) {
        orderService.changeOrderStatus(orderDTO);
    }

    @GetMapping("/order/content")
    public List<OrderContentDTO> orderContent(@RequestParam(value = "orderId") Integer orderId,
                                              @RequestParam(value= "loadImage", defaultValue = "false")
                                                      boolean loadImage) {
        return orderService.getOrderContentList(orderId, loadImage);
    }

    @GetMapping("/user/history/orders")
    public List<OrderDTO> userOrders(){
        return  orderService.getUserOrders();
    }

}
