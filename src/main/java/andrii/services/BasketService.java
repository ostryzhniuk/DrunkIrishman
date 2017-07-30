package andrii.services;

import andrii.dto.BasketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    private BasketSet<BasketDTO> basketSet;

    public BasketSet<BasketDTO> addToBasket(BasketDTO basketDTO) {
        basketSet.add(basketDTO);
        return basketSet;
    }

    public BasketSet<BasketDTO> decrease(BasketDTO basketDTO) {
        basketSet.decrease(basketDTO);
        return basketSet;
    }

    public Integer getBasketSetSize() {
        return basketSet.size();
    }

    public BasketSet<BasketDTO> getBasketSet() {
        return basketSet;
    }

    public BasketSet<BasketDTO> removeFromBasket(BasketDTO basketDTO) {
        basketSet.remove(basketDTO);
        return basketSet;
    }
}
