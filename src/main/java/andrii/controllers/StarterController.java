package andrii.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StarterController {

    @RequestMapping({"/", "/home"})
    public String mainPage() {
        return "/home.page/index";
//        return "/angular.test/app/index";
    }

}
