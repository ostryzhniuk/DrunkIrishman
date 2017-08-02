package andrii.controllers;

import andrii.dto.OrderDTO;
import andrii.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public List<OrderDTO> orders(@RequestBody OrderDTO.Status status){
        return  orderService.getOrders(status);
    }

}
