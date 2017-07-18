package andrii.dto;

import andrii.entities.Product;
import org.modelmapper.ModelMapper;
import java.math.BigDecimal;

public class ProductDTO {

    private Integer id;
    private String name;
    private BigDecimal capacity;
    private BigDecimal price;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product convertToEntity() {
        return new ModelMapper().map(this, Product.class);
    }

    public static ProductDTO convertToDTO(Product product) {
        return new ModelMapper().map(product, ProductDTO.class);
    }

}
