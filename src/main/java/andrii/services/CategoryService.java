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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public List<CategoryDTO> getCategories(boolean loadPhoto) {
        List<CategoryDTO> categoryList = convertToDTOList(categoryDAO.getObjects());
        categoryList.sort(Comparator.comparing(CategoryDTO::getName));
        if (loadPhoto) {
            categoryList.forEach(category -> category.setPhoto(loadPhoto(category.getId())));
        }
        return categoryList;
    }

    @Transactional
    public void save (CategoryDTO categoryDTO) {
        Category category = categoryDTO.convertToEntity();
        categoryDAO.save(category);
        savePhoto(categoryDTO.getPhoto(), category.getId());
    }

    @Transactional
    public CategoryDTO getCategoryByName(String categoryName) {
        Category category = categoryDAO.getCategory(categoryName);
        if (category != null) {
            return CategoryDTO.convertToDTO(category);
        } else {
            throw new NullPointerException();
        }
    }

    @Transactional
    public void update(CategoryDTO categoryDTO) {
        Category category = buildCategory(categoryDTO);
        categoryDAO.update(category);
        savePhoto(categoryDTO.getPhoto(), category.getId());
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

    public String loadPhoto(Integer categoryId){
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "categories" + separator + categoryId + ".jpg");
        return ImageHandler.loadEncodedImage(path);
    }

    private void savePhoto(String photoBASE64, Integer categoryId) {
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "categories" + separator + categoryId + ".jpg");

        ImageHandler.save(ImageHandler.decodeBase64Image(photoBASE64), path);
    }

    @Transactional
    public Category getCategory(Integer categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Transactional
    public Category buildCategory(CategoryDTO categoryDTO){
        Category category = getCategory(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.isActive());
        return category;
    }

}
