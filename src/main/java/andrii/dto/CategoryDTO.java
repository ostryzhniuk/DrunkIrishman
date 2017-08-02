package andrii.dto;

import andrii.entities.Category;
import com.univocity.parsers.annotations.Parsed;
import org.modelmapper.ModelMapper;

import java.io.Serializable;

public class CategoryDTO {

    private Integer id;
    private String name;
    private String photo;

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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category convertToEntity() {
        return new ModelMapper().map(this, Category.class);
    }

    public static CategoryDTO convertToDTO(Category category) {
        return new ModelMapper().map(category, CategoryDTO.class);
    }

}
