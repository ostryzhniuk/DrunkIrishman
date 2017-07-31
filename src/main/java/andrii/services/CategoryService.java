package andrii.services;

import andrii.utils.ImageHandler;
import andrii.dao.CategoryDAO;
import andrii.dto.CategoryDTO;
import andrii.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public List<CategoryDTO> getCategories(boolean loadPhoto) {
        List<CategoryDTO> categoryList = convertToDTOList(categoryDAO.getObjects());
        if (loadPhoto) {
            categoryList.forEach(category -> category.setPhoto(loadPhoto(category)));
        }
        return categoryList;
    }

    @Transactional
    public void create (CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.save(category);
        savePhoto(categoryDTO.getPhoto(), category.getId());
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

    @Transactional
    public void deactivate(CategoryDTO categoryDTO){
        categoryDAO.deactivate(categoryDTO.getId());
    }

    private List<CategoryDTO> convertToDTOList(List<Category> categoryList) {
        return categoryList
                .stream()
                .map(category -> CategoryDTO.convertToDTO(category))
                .collect(Collectors.toList());
    }

    private String loadPhoto(CategoryDTO categoryDTO){
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "categories" + separator + categoryDTO.getId() + ".jpg");
        return ImageHandler.loadEncodedImage(path);
    }

    private void savePhoto(String photoBASE64, Integer categoryId) {
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "categories" + separator + categoryId + ".jpg");

        ImageHandler.save(ImageHandler.decodeBASE64(photoBASE64), path);
    }

}
