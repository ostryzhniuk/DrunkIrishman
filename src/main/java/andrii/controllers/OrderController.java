package andrii.controllers;

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

}
