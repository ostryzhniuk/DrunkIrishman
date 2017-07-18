package andrii.controllers;

import andrii.core.BasketSet;
import andrii.dto.BasketDTO;
import andrii.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/addToBasket")
    public Integer addToBasket(@RequestBody BasketDTO basketDTO, HttpSession httpSession){
        BasketSet basketSet = orderService.addToBasket(basketDTO);
        httpSession.setAttribute("basketSet", basketSet);
        httpSession.setAttribute("goodsQuantity", basketSet.size());
        Integer goodsQuantity = basketSet.size();
        return goodsQuantity;
    }

}
