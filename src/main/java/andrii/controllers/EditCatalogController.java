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
        categoryService.save(categoryDTO);
    }

    @PutMapping("/category/update")
    public void updateCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
    }

    @PutMapping("/category/deactivate")
    public void deactivateCategory (@RequestBody CategoryDTO categoryDTO) {
        categoryService.deactivate(categoryDTO);
    }

    @PostMapping("/product/create")
    public ProductDTO createProduct (@RequestBody ProductDTO productDTO) {
        return productService.save(productDTO);
    }

    @PutMapping("/product/update")
    public void updateProduct (@RequestBody ProductDTO productDTO) {
        productService.update(productDTO);
    }

    @PutMapping("/product/deactivate")
    public void deactivateProduct (@RequestBody ProductDTO productDTO) {
        productService.deactivate(productDTO);
    }
}
