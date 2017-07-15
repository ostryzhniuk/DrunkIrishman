package andrii.controllers;

import andrii.dto.LoginDTO;
import andrii.dto.UserDTO;
import andrii.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/")
    public String mainPage() {
        return "/home.page/index";
//        return "/angular.test/app/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @PostMapping("/postEmail")
    @ResponseBody
    public String postEmail(@RequestBody LoginDTO loginDTO) {
        LOGGER.info("email: " + loginDTO.getEmail());
        LOGGER.info("password: " + loginDTO.getPassword());
        return HttpStatus.CREATED.toString();
    }

    @PostMapping("/signUp")
    @ResponseBody
    public String signUp(@RequestBody UserDTO userDTO) {
        Integer success = userService.save(userDTO);
        return HttpStatus.CREATED.toString();
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user";
    }

    @RequestMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
