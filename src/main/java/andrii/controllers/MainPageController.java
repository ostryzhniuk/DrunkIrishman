package andrii.controllers;

import andrii.entities.Product;
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
public class MainPageController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/photo/{name}")
    public byte[] getImage(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + name + ".jpg");
        return IOUtils.toByteArray(inputStream);
    }

}
