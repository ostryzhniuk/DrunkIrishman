package andrii.controllers;

import andrii.utils.ImageHandler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileController {

    @GetMapping("/category/image/{categoryId}")
    public ResponseEntity<String> categoryImage(@PathVariable("categoryId") Integer categoryId) {
        String separator = FileSystems.getDefault().getSeparator();
        Path path = Paths.get("C:" + separator + "DrunkIrishman" + separator + "images"
                + separator + "categories" + separator + categoryId + ".jpg");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(ImageHandler.loadEncodedImage(path));

    }

}
