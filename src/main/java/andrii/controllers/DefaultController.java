package andrii.controllers;

import andrii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/main", "/"})
    public String mainPage () {
        return "Hello!";
    }

    @RequestMapping("/hello")
    public String hello () {
        return "Hello " + userService.getFirstUser().getName();
    }

}
