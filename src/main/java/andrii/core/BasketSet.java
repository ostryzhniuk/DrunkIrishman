package andrii.core;

import andrii.dto.BasketDTO;
import java.util.HashSet;

public class BasketSet<T extends BasketDTO> extends HashSet<T> {

    @Override
    public boolean add(T t) {
        if (!super.add(t)) {
            getObjectFromSet(t).increaseCounter();
            return false;
        }
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
