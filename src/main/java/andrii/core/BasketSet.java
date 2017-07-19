package andrii.core;

import andrii.dto.BasketDTO;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/** * Annotation-based configuration of session scope */
@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BasketSet<T extends BasketDTO> extends HashSet<T> {

    private Integer size = 0;

    @Override
    public boolean add(T t) {
        if (!super.add(t)) {
            getObjectFromSet(t).increaseCounter();
        }
        size();
        return true;
    }

    @Override
    public boolean remove(Object o) {

        T itemToRemove;
        if ((itemToRemove = getObjectFromSet((T) o)) != null) {

            if (itemToRemove.getCounter() > 1) {
                itemToRemove.decreaseCounter();
            } else {
                super.remove(itemToRemove);
            }
            size();
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        size = stream()
                .map(item -> item.getCounter())
                .reduce((integer, integer2) -> integer + integer2)
                .get();

        return size;
    }

    private T getObjectFromSet(T wantedObject) {
        for (T t: this) {
            if (t.equals(wantedObject)) {
                return t;
            }
        }
        return null;
    }

    public Integer getSize() {
        return size;
    }
}
