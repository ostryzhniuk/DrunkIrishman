package andrii.dto;

import andrii.entities.Order;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {

    private Integer id;
    @JsonFormat(pattern = "dd.MM.yyyy, HH:mm:ss")
    private LocalDateTime date;
    private String address;
    private String phone;
    private BigDecimal price;
    private boolean delivery = false;
    private UserDTO user;
    @JsonIgnore
    private List<OrderContentDTO> contentList;
    private Status status = Status.IN_PROCESS;

    public enum Status {
        IN_PROCESS, IS_SENT, COMPLETED
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<OrderContentDTO> getContentList() {
        return contentList;
    }

    public void setContentList(List<OrderContentDTO> contentList) {
        this.contentList = contentList;
    }

    public Order convertToEntity() {
        Order order = new ModelMapper().map(this, Order.class);
        order.setUser(this.getUser().convertToEntity());
        return order;
    }

    public static OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new ModelMapper().map(order, OrderDTO.class);
        orderDTO.setUser(UserDTO.convertToDTO(order.getUser()));
        return orderDTO;
    }

    public static Order.Status convertToEntityStatus(Status status) {
        return Enum.valueOf(Order.Status.class, status.toString());
    }

}
