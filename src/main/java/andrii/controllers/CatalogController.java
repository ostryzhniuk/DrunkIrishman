package andrii.controllers;

import andrii.entities.Category;
import andrii.entities.Product;
import andrii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/{categoryName}")
    public List<Product> products(@PathVariable("categoryName") String categoryName){
        return productService.getProductsByCategory(categoryName);
    }

    @GetMapping("/categories")
    public List<Category> categories(){
        return  productService.getCategories();
    }

}
