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
    private CategoryDTO category;
    private StockDTO.Status status;

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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public StockDTO.Status getStatus() {
        return status;
    }

    public void setStatus(StockDTO.Status status) {
        this.status = status;
    }

    public Product convertToEntity() {
        return new ModelMapper().map(this, Product.class);
    }

    public static ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
        productDTO.setCategory(CategoryDTO.convertToDTO(product.getCategory()));
        return productDTO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ProductDTO productDTO = (ProductDTO) obj;
        return this.name.equals(productDTO.getName());
    }

}
