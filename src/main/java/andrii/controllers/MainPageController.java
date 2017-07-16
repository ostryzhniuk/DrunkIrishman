package andrii.controllers;

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

    @GetMapping("/photo/{name}")
    public byte[] getImage(@PathVariable("name") String name) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/" + name + ".jpg");
        return IOUtils.toByteArray(inputStream);
    }

}
