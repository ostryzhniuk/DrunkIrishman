package andrii.controllers;

import andrii.services.BasketSet;
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

    @PutMapping("/decrease")
    public Integer decrease(@RequestBody BasketDTO basketDTO){
        return basketService.decrease(basketDTO).size();
    }

    @PutMapping("/removeFromBasket")
    public Integer removeFromBasket(@RequestBody BasketDTO basketDTO){
        return basketService.removeFromBasket(basketDTO).size();
    }

    @GetMapping("/basketSize")
    public Integer basketSize() {
        return basketService.getBasketSetSize();
    }

    @GetMapping("/basket")
    public BasketSet basket(){
        return basketService.getBasketSet();
    }


}
