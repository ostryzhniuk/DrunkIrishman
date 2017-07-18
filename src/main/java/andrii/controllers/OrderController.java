package andrii.controllers;

import andrii.dto.BasketDTO;
import andrii.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/addToBasket")
    public Integer addToBasket(@RequestBody BasketDTO basketDTO){
        return orderService.addToBasket(basketDTO).size();
    }

    @GetMapping("/goodsQuantity")
    public Integer goodsQuantity() {
        return orderService.getBasketSetSize();
    }


}
