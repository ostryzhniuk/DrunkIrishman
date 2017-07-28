package andrii.services;

import andrii.dao.CategoryDAO;
import andrii.dto.CategoryDTO;
import andrii.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public void create (CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.save(category);
    }

}
