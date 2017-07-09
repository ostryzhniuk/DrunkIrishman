package andrii.controllers;

import andrii.entities.User;
import andrii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/", "/main"})
    public String mainPage() {
        return "mainPage";
    }

    @RequestMapping("/hello")
    public String hello () {
        return "Hello " + userService.getFirstUser().getName();
    }

}
