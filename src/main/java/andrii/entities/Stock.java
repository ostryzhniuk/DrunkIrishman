package andrii.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger bar_code;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private String status;

    public Stock() {
    }

    public BigInteger getBar_code() {
        return bar_code;
    }

    public void setBar_code(BigInteger bar_code) {
        this.bar_code = bar_code;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
