package andrii.services;

import andrii.core.BasketSet;
import andrii.dto.BasketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private BasketSet<BasketDTO> basketSet;

    public BasketSet<BasketDTO> addToBasket(BasketDTO basketDTO) {
        basketSet.add(basketDTO);
        return basketSet;
    }

    public BasketSet<BasketDTO> removefromBasket(BasketDTO basketDTO) {
        basketSet.remove(basketDTO);
        return basketSet;
    }

}
