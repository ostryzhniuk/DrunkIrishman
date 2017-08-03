package andrii.services;

import andrii.dao.ProductDAO;
import andrii.dto.ProductDTO;
import andrii.entities.Product;
import andrii.utils.CSVHandler;
import andrii.utils.ImageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static andrii.dto.ProductDTO.*;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private CategoryService categoryService;

    @Value("${resources.path}")
    private String resourcesPath;

    @Transactional
    public List<ProductDTO> getProductsByCategory(String categoryName, boolean loadPhoto) {
        List<Product> products = productDAO.getProductsByCategory(categoryName);
        List<ProductDTO> productDTOList = convertToDTOList(products);
        if (loadPhoto) {
            productDTOList.forEach(productDTO -> productDTO.setPhoto(loadPhoto(productDTO.getId())));
        }
        return productDTOList;
    }

    @Transactional
    public ProductDTO getProductDTOById(Integer productId) {
        Product product = productDAO.getProductById(productId);
        return convertToDTO(product);
    }

    @Transactional
    public Product getProductById(Integer productId) {
        return productDAO.getProductById(productId);
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
                .map(product -> convertToDTO(product))
                .collect(Collectors.toList());
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productDTO.convertToEntity();
        productDAO.save(product);
        savePhoto(productDTO.getPhoto(), product.getId());
        return convertToDTO(product);
    }

    @Transactional
    public void save(List<ProductDTO> productList) {
        productList
                .stream()
                .map(product -> product.convertToEntity())
                .forEach(product -> productDAO.updatePriceAndStatus(product));
    }

    @Transactional
    public void update(ProductDTO productDTO) {
        Product product = buildProduct(productDTO);
        productDAO.update(product);
        savePhoto(productDTO.getPhoto(), product.getId());
    }

    @Transactional
    public void deactivate(ProductDTO productDTO) {
        productDAO.deactivate(productDTO.getId());
    }

    public String loadPhoto(Integer productId){
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get(resourcesPath + "DrunkIrishman" + separator + "images"
                + separator + "products" + separator + productId + ".jpg");
        return ImageHandler.loadEncodedImage(path);
    }

    private void savePhoto(String photoBASE64, Integer productId) {
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get(resourcesPath + "DrunkIrishman" + separator + "images"
                + separator + "products" + separator + productId + ".jpg");

        ImageHandler.save(ImageHandler.decodeBase64Image(photoBASE64), path);
    }

    @Transactional
    public void createProductByCsv(String base64SourceData) {
        save(CSVHandler.parseProducts(base64SourceData));
    }

    @Transactional
    public Product buildProduct(ProductDTO productDTO) {
        Product product = productDAO.getProductById(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategory(productDTO.getCategory().getId()));
        product.setCapacity(productDTO.getCapacity());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setStatus(convertToEntityStatus(productDTO.getStatus()));
        product.setActive(productDTO.isActive());
        return product;
    }

}
