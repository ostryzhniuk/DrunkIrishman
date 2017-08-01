package andrii.services;

import andrii.dto.CartDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashSet;

/** * Annotation-based configuration of session scope */
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartSet<T extends CartDTO> extends HashSet<T> {

    @Override
    public boolean add(T t) {
        if (!super.add(t)) {
            get(t).increaseQuantity(t.getQuantity());
        }
        return true;
    }

    public boolean decrease(T t) {

        T itemToRemove;
        if ((itemToRemove = get(t)) != null) {

            if (itemToRemove.getQuantity() > 1) {
                itemToRemove.decreaseQuantity();
            } else {
                super.remove(itemToRemove);
            }
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.stream()
                .mapToInt(CartDTO::getQuantity)
                .sum();
    }

    private T get(T t) {
        return this.stream()
                .filter(product -> product.equals(t))
                .findAny()
                .get();
    }

    public BigDecimal sum(){
        return this.stream()
                .map(c -> c.getPrice().multiply(new BigDecimal(c.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
