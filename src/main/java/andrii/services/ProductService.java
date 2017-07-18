package andrii.services;

import andrii.dao.CategoryDAO;
import andrii.dao.ProductDAO;
import andrii.dto.ProductDTO;
import andrii.entities.Category;
import andrii.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
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
    public List<ProductDTO> getProductsByCategory(String categoryName) {
        List<Product> products = productDAO.getProductsByCategory(categoryName);
        List<ProductDTO> dtoList = new LinkedList<>();
        for (Product product : products) {
            dtoList.add(ProductDTO.convertToDTO(product));
        }
        return dtoList;
    }

    @Transactional
    public ProductDTO getProductById(Integer productId) {
        Product product = productDAO.getProductById(productId);
        return product == null ? null : ProductDTO.convertToDTO(product);
    }
}
