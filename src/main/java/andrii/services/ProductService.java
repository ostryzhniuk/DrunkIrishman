package andrii.services;

import andrii.dao.CategoryDAO;
import andrii.dao.ProductDAO;
import andrii.dto.ProductDTO;
import andrii.entities.Category;
import andrii.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return convertToDTOList(products);
    }

    @Transactional
    public ProductDTO getProductById(Integer productId) {
        Product product = productDAO.getProductById(productId);
        return ProductDTO.convertToDTO(product);
    }

    @Transactional
    public List<ProductDTO> search(String parameter) {
        List<Product> products = productDAO.getProductByName(parameter);
        return convertToDTOList(products);
    }

    private List<ProductDTO> convertToDTOList(List<Product> productList) {
        return productList
                .stream()
                .map(product -> ProductDTO.convertToDTO(product))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productDTO.convertToEntity();
        productDAO.save(product);
        return ProductDTO.convertToDTO(product);
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        productDAO.update(productDTO.convertToEntity());
    }

}
