package andrii.controllers;

import andrii.core.BasketSet;
import andrii.dto.BasketDTO;
import andrii.dto.ProductDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

@RestController
public class OrderController {

    @PutMapping("/addToBasket")
    public void addToBasket(@RequestBody ProductDTO productDTO, HttpSession httpSession){
//        httpSession.setAttribute();
    }

}
