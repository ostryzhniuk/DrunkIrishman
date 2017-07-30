package andrii.services;

import andrii.dto.CartDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import java.util.HashSet;

/** * Annotation-based configuration of session scope */
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartSet<T extends CartDTO> extends HashSet<T> {

    @Override
    public boolean add(T t) {
        if (!super.add(t)) {
            get(t).increaseCounter(t.getCounter());
        }
        return true;
    }

    public boolean decrease(T t) {

        T itemToRemove;
        if ((itemToRemove = get(t)) != null) {

            if (itemToRemove.getCounter() > 1) {
                itemToRemove.decreaseCounter();
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
                .mapToInt(CartDTO::getCounter)
                .sum();
    }

    private T get(T t) {
        return this.stream()
                .filter(product -> product.equals(t))
                .findAny()
                .get();
    }

}
