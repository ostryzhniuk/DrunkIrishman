package andrii.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String mainPage() {
        return "/home.page/home-page.template";
//        return "/angular.test/app/index";
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
