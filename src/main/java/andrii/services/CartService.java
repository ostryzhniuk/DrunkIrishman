package andrii.services;

import andrii.dto.CartDTO;
import andrii.dto.ProductDTO;
import andrii.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class CartService {

    @Autowired
    private CartSet<CartDTO> cartSet;

    @Autowired
    private ProductService productService;

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

    public CartSet<CartDTO> getCartSet(boolean loadPhoto) {
        if (loadPhoto) {
           cartSet.forEach(product -> product.setPhoto(productService.loadPhoto(product.getId())));
        }
        return cartSet;
    }

    public CartSet<CartDTO> remove(CartDTO cartDTO) {
        cartSet.remove(cartDTO);
        return cartSet;
    }

    public BigDecimal sum() {
        return cartSet.sum();
    }

    public void clearCart(){
        cartSet.clear();
    }
}
