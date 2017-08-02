package andrii.controllers;

import andrii.dto.CategoryDTO;
import andrii.dto.ProductDTO;
import andrii.services.CategoryService;
import andrii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CatalogController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products/{categoryName}")
    public List<ProductDTO> products(@PathVariable("categoryName") String categoryName,
                                     @RequestParam(value= "loadImage", defaultValue = "false") boolean loadImage){
        return productService.getProductsByCategory(categoryName, loadImage);
    }

    @GetMapping("/categories")
    public List<CategoryDTO> categories(@RequestParam(value= "loadImage", defaultValue = "false") boolean loadImage){
        return categoryService.getCategories(loadImage);
    }

    @GetMapping("/product/{productId}")
    public ProductDTO product(@PathVariable("productId") Integer productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/search/{parameter}")
    public List<ProductDTO> search(@PathVariable("parameter") String parameter){
        return productService.search(parameter);
    }

    @GetMapping("/category/{categoryName}")
    public CategoryDTO categoryByName (@PathVariable("categoryName") String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }

    @GetMapping("/product/status/list")
    public ProductDTO.Status[] statusList () {
        return ProductDTO.Status.values();
    }

}
