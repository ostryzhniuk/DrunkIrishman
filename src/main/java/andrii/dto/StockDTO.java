package andrii.dto;

import andrii.entities.Product;

public class StockDTO {

    private Long bar_code;
    private Product product;

    public enum Status {
        IN_STOCK, SOLD_OUT, CHARGE_OFF
    }

    private Status status;

    public Long getBar_code() {
        return bar_code;
    }

    public void setBar_code(Long bar_code) {
        this.bar_code = bar_code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
