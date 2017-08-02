package andrii.controllers;

import andrii.services.CategoryService;
import andrii.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FileController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/image/{categoryId}")
    public ResponseEntity<String> categoryImage(@PathVariable("categoryId") Integer categoryId) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(categoryService.loadPhoto(categoryId));

    }

    @GetMapping("/product/image/{productId}")
    public ResponseEntity<String> productImage(@PathVariable("productId") Integer productId) {
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(productService.loadPhoto(productId));
    }

    @PostMapping("/product/create/byCsv")
    public void createProductByCsv(@RequestBody String base64SourceData){
        productService.createProductByCsv(base64SourceData);
    }

}
