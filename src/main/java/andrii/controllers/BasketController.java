package andrii.controllers;

import andrii.core.BasketSet;
import andrii.dto.BasketDTO;
import andrii.services.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PutMapping("/addToBasket")
    public Integer addToBasket(@RequestBody BasketDTO basketDTO){
        return basketService.addToBasket(basketDTO).size();
    }

    @GetMapping("/goodsQuantity")
    public Integer goodsQuantity() {
        return basketService.getBasketSetSize();
    }

    @GetMapping("/basket")
    public BasketSet basket(){
        return basketService.getBasketSet();
    }


}
