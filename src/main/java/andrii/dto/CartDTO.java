package andrii.dto;

public class CartDTO extends ProductDTO  {

    private Integer quantity = 1;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(Integer value){
        quantity += value;
    }

    public void decreaseQuantity(){
        quantity--;
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
