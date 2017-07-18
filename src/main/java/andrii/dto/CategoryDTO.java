package andrii.dto;

import andrii.entities.Category;
import org.modelmapper.ModelMapper;

public class CategoryDTO {

    private Integer id;
    private String name;

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

    public Category convertToEntity() {
        return new ModelMapper().map(this, Category.class);
    }

    public static CategoryDTO convertToDTO(Category category) {
        return new ModelMapper().map(category, CategoryDTO.class);
    }

}
