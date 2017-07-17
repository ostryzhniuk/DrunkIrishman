package andrii.services;

import andrii.dao.CategoryDAO;
import andrii.dao.ProductDAO;
import andrii.entities.Category;
import andrii.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public List<Category> getCategories(){
        return categoryDAO.getObjects();
    }

    @Transactional
    public List<Product> getProductsByCategory(String categoryName) {
        return productDAO.getProductsByCategory(categoryName);
    }
}
