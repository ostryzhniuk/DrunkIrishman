package andrii.controllers;

import andrii.services.CartSet;
import andrii.dto.CartDTO;
import andrii.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PutMapping("/addToCart")
    public Integer add(@RequestBody CartDTO cartDTO){
        return cartService.add(cartDTO).size();
    }

    @PutMapping("/decrease")
    public Integer decrease(@RequestBody CartDTO cartDTO){
        return cartService.decrease(cartDTO).size();
    }

    @PutMapping("/removeFromCart")
    public Integer remove(@RequestBody CartDTO cartDTO){
        return cartService.remove(cartDTO).size();
    }

    @GetMapping("/cartSize")
    public Integer cartSize() {
        return cartService.getCartSetSize();
    }

    @GetMapping("/cart")
    public CartSet cart(@RequestParam(value= "loadImage", defaultValue = "false") boolean loadImage){
        return cartService.getCartSet(loadImage);
    }


}
