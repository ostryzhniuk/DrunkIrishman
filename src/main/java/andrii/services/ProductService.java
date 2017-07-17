package andrii.services;

import andrii.dao.CategoryDao;
import andrii.dao.ProductDao;
import andrii.entities.Category;
import andrii.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public List<Category> getCategories(){
        return categoryDao.getObjects();
    }

    @Transactional
    public List<Product> getProductsByCategory(String categoryName) {
        return productDao.getProductsByCategory(categoryName);
    }
}
