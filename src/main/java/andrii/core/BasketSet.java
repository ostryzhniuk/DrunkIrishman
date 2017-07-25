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

    @Override
    public boolean add(T t) {
        if (!super.add(t)) {
            getObjectFromSet(t).increaseCounter();
        }
        return true;
    }

    public boolean decrease(T t) {

        T itemToRemove;
        if ((itemToRemove = getObjectFromSet(t)) != null) {

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
        Integer size = 0;
        for (T t : this) {
            size += t.getCounter();
        }
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

}
