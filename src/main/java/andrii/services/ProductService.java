package andrii.services;

import andrii.dao.ProductDAO;
import andrii.dto.ProductDTO;
import andrii.entities.Product;
import andrii.utils.CSVHandler;
import andrii.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Transactional
    public List<ProductDTO> getProductsByCategory(String categoryName, boolean loadPhoto) {
        List<Product> products = productDAO.getProductsByCategory(categoryName);
        List<ProductDTO> productDTOList = convertToDTOList(products);
        if (loadPhoto) {
            productDTOList.forEach(productDTO -> productDTO.setPhoto(loadPhoto(productDTO.getId())));
        }
        CSVHandler.write(productDTOList.get(0));
        return productDTOList;
    }

    @Transactional
    public ProductDTO getProductById(Integer productId) {
        Product product = productDAO.getProductById(productId);
        return ProductDTO.convertToDTO(product);
    }

    @Transactional
    public List<ProductDTO> search(String parameter) {
        List<Product> products = productDAO.getProductByName(parameter);
        List<ProductDTO> productDTOList = convertToDTOList(products);
        productDTOList.forEach(productDTO -> productDTO.setPhoto(loadPhoto(productDTO.getId())));
        return productDTOList;
    }

    public List<ProductDTO> convertToDTOList(List<Product> productList) {
        return productList
                .stream()
                .map(product -> ProductDTO.convertToDTO(product))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productDTO.convertToEntity();
        productDAO.save(product);
        savePhoto(productDTO.getPhoto(), product.getId());
        return ProductDTO.convertToDTO(product);
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = productDTO.convertToEntity();
        productDAO.update(product);
        savePhoto(productDTO.getPhoto(), product.getId());
    }

    @Transactional
    public void deactivate(ProductDTO productDTO) {
        productDAO.deactivate(productDTO.getId());
    }

    public String loadPhoto(Integer productId){
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "products" + separator + productId + ".jpg");
        return ImageHandler.loadEncodedImage(path);
    }

    private void savePhoto(String photoBASE64, Integer productId) {
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "products" + separator + productId + ".jpg");

        ImageHandler.save(ImageHandler.decodeBASE64(photoBASE64), path);
    }

}
