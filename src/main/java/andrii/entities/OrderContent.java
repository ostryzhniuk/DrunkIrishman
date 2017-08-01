package andrii.entities;

import andrii.dto.CartDTO;

import javax.persistence.*;

@Entity
@Table(name = "order_content")
public class OrderContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static OrderContent createOrderContent(CartDTO cartDTO, Order order){
        OrderContent orderContent = new OrderContent();
        orderContent.setOrder(order);
        orderContent.setProduct(cartDTO.convertToEntity());
        orderContent.setQuantity(cartDTO.getQuantity());
        return orderContent;
    }
}
