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

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    /*@GetMapping("/getCategories")
    public List<Category> getCategories(){
        
    }*/

}