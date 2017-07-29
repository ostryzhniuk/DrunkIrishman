package andrii.services;

import andrii.core.ImageHandler;
import andrii.dao.CategoryDAO;
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

    @Transactional
    public void create (CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.save(category);

        ImageHandler.save(categoryDTO.getPhoto(), category.getId().toString());
//        BufferedImage bufferedImage = ImageHandler.decodeBASE64(categoryDTO.getPhoto());
//        ImageHandler.save(bufferedImage, category.getId().toString());
    }

}