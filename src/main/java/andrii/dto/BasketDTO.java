package andrii.dto;

public class BasketDTO extends ProductDTO  {

    private Integer counter = 1;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public void increaseCounter(){
        counter++;
    }

    public void decreaseCounter(){
        counter--;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        BasketDTO basketDTO = (BasketDTO) obj;

        return getId().equals(basketDTO.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
