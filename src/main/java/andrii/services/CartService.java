package andrii.services;

import andrii.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartSet<CartDTO> cartSet;

    public CartSet<CartDTO> add(CartDTO cartDTO) {
        cartSet.add(cartDTO);
        return cartSet;
    }

    public CartSet<CartDTO> decrease(CartDTO cartDTO) {
        cartSet.decrease(cartDTO);
        return cartSet;
    }

    public Integer getCartSetSize() {
        return cartSet.size();
    }

    public CartSet<CartDTO> getCartSet() {
        return cartSet;
    }

    public CartSet<CartDTO> remove(CartDTO cartDTO) {
        cartSet.remove(cartDTO);
        return cartSet;
    }
}
