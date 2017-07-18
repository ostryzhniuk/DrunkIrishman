package andrii.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bar_code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public enum Status {
        IN_STOCK, SOLD_OUT, CHARGE_OFF
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
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
