package andrii.dto;

import andrii.entities.Order;
import andrii.entities.OrderContent;
import andrii.entities.Product;
import org.modelmapper.ModelMapper;

public class OrderContentDTO {

    private Integer id;
    private OrderDTO order;
    private Product product;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderContent convertToEntity() {
        OrderContent orderContent = new ModelMapper().map(this, OrderContent.class);
        orderContent.setOrder(this.getOrder().convertToEntity());
        return orderContent;
    }

    public static OrderContentDTO convertToDTO(OrderContent orderContent) {
        return new ModelMapper().map(orderContent, OrderContentDTO.class);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
