package andrii.controllers;

import andrii.dto.ProductDTO;
import andrii.entities.Category;
import andrii.services.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{categoryName}")
    public List<ProductDTO> products(@PathVariable("categoryName") String categoryName){
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/categories")
    public List<Category> categories(){
        return  productService.getCategories();
    }

    @GetMapping("/product/{productId}")
    public ProductDTO product(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/search/{parameter}")
    public List<ProductDTO> search(@PathVariable("parameter") String parameter){
        return productService.search(parameter);
    }

    /*@GetMapping("/photo/{name}")
    public byte[] getImage(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + name + ".jpg");
        return IOUtils.toByteArray(inputStream);
    }*/

}
