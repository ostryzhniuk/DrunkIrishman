package andrii.dto;

public class GenericDTO<E> {

    private E item;

    public E getItem() {
        return item;
    }

    public void setItem(E item) {
        this.item = item;
    }
}
