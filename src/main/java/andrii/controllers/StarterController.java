package andrii.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StarterController {

    @RequestMapping({"/", "home"})
    public String mainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            System.out.println(authentication.getName());
        }
        return "/home.page/index";
//        return "/angular.test/app/index";
    }

}
