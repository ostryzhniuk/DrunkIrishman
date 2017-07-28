package andrii.services;

import andrii.dao.CategoryDAO;
import andrii.dao.ProductDAO;
import andrii.dto.ProductDTO;
import andrii.dto.StockDTO;
import andrii.entities.Category;
import andrii.entities.Product;
import andrii.entities.Stock;
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
        return convertToDTOList(products);
    }

    @Transactional
    public ProductDTO getProductById(Integer productId) {
        Product product = productDAO.getProductById(productId);
        return product == null ? null : buildProductDTO(product);
    }

    @Transactional
    public List<ProductDTO> search(String parameter) {
        List<Product> products = productDAO.getProductByName(parameter);
        return convertToDTOList(products);
    }

    private List<ProductDTO> convertToDTOList(List<Product> productList) {
        List<ProductDTO> dtoList = new LinkedList<>();
        for (Product product : productList) {
            dtoList.add(buildProductDTO(product));
        }
        return dtoList;
    }

    private ProductDTO buildProductDTO(Product product) {
        ProductDTO productDTO = ProductDTO.convertToDTO(product);
        Integer inStockNumber = getProductsWithStatus(product.getId(), Stock.Status.IN_STOCK).size();

        productDTO.setStatus(inStockNumber == 0 ? StockDTO.Status.SOLD_OUT : StockDTO.Status.IN_STOCK);
        return productDTO;
    }

    @Transactional
    public List<Product> getProductsWithStatus(Integer productId, Stock.Status status) {
        return productDAO.getProductsWithStatus(productId, status);
    }

}
