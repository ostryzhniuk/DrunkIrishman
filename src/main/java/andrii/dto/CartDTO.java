package andrii.dto;

public class CartDTO extends ProductDTO  {

    private Integer counter = 1;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void increaseCounter(Integer value){
        counter += value;
    }

    public void decreaseCounter(){
        counter--;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        CartDTO cartDTO = (CartDTO) obj;

        return getId().equals(cartDTO.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
