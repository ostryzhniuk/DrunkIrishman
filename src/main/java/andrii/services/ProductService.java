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
    public List<Product> getProducts() {
        return productDao.getObjects();
    }

    @Transactional
    public List<Category> getCategories(){
        return categoryDao.getObjects();
    }
}
