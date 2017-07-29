package andrii.controllers;

import andrii.dto.CategoryDTO;
import andrii.dto.ProductDTO;
import andrii.entities.Category;
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

    @PostMapping("/category/create")
    public void createCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
    }

    @GetMapping("/category/{categoryName}")
    public CategoryDTO categoryByName (@PathVariable("categoryName") String categoryName) {
        return categoryService.getCategoryByName(categoryName);
    }

    @PutMapping("/category/update")
    public void updateCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
    }

    /*@GetMapping("/photo/{name}")
    public byte[] getImage(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + name + ".jpg");
        return IOUtils.toByteArray(inputStream);
    }*/

}
