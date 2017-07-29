package andrii.controllers;

import andrii.dto.CategoryDTO;
import andrii.dto.ProductDTO;
import andrii.services.CategoryService;
import andrii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EditCatalogController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category/create")
    public void createCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.create(categoryDTO);
    }

    @PutMapping("/category/update")
    public void updateCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
    }

    @DeleteMapping("/category/delete/{categoryId}")
    public void deleteCategory (@PathVariable("categoryId") Integer categoryId) {
        categoryService.delete(categoryId);
    }

    @PostMapping("/product/create")
    public void createProduct (@RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
    }

    @PutMapping("/product/update")
    public void updateProduct (@RequestBody ProductDTO productDTO) {
        productService.update(productDTO);
    }
}
