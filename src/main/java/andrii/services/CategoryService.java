package andrii.services;

import andrii.core.ImageHandler;
import andrii.dao.CategoryDAO;
import andrii.dao.ProductDAO;
import andrii.dto.CategoryDTO;
import andrii.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.image.BufferedImage;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public void create (CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.save(category);

        ImageHandler.save(categoryDTO.getPhoto(), category.getId().toString());
//        BufferedImage bufferedImage = ImageHandler.decodeBASE64(categoryDTO.getPhoto());
//        ImageHandler.save(bufferedImage, category.getId().toString());
    }

    @Transactional
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryDAO.getCategoryByName(categoryName);
        if (category != null) {
            return CategoryDTO.convertToDTO(category);
        } else {
            throw new NullPointerException();
        }
    }

    @Transactional
    public void update(CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.update(category);
    }

    public void delete(Integer categoryId){
        productDAO.deleteByCategory(categoryId);
        categoryDAO.deleteById(categoryId);
    }

}
