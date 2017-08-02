package andrii.dto;

import andrii.entities.OrderContent;
import org.modelmapper.ModelMapper;

public class OrderContentDTO {

    private Integer id;
    private OrderDTO order;
    private ProductDTO product;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public OrderContent convertToEntity() {
        OrderContent orderContent = new ModelMapper().map(this, OrderContent.class);
        orderContent.setOrder(this.getOrder().convertToEntity());
        return orderContent;
    }

    public static OrderContentDTO convertToDTO(OrderContent orderContent) {
        OrderContentDTO orderContentDTO = new ModelMapper().map(orderContent, OrderContentDTO.class);
        orderContentDTO.setProduct(ProductDTO.convertToDTO(orderContent.getProduct()));
        orderContentDTO.setOrder(OrderDTO.convertToDTO(orderContent.getOrder()));
        return orderContentDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
