package andrii.controllers;

import andrii.entities.Category;
import andrii.entities.Product;
import andrii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> products(){
        return productService.getProducts();
    }

    @GetMapping("/categories")
    public List<Category> categories(){
        System.out.println("getCategories");
        return  productService.getCategories();
    }

}
