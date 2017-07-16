package andrii.controllers;

import andrii.dto.LoginDTO;
import andrii.dto.UserDTO;
import andrii.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DefaultController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @RequestMapping("/")
    public String mainPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            System.out.println(authentication.getName());
        }
        return "/home.page/index";
//        return "/angular.test/app/index";
    }

    @RequestMapping("/home")
    public String home() {
        return "/home";
    }

    @RequestMapping("/authorize")
    public String authorize(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest) {
        LOGGER.info("username: " + loginDTO.getUsername());
        LOGGER.info("password: " + loginDTO.getPassword());
        httpServletRequest.setAttribute("username", loginDTO.getUsername());
        httpServletRequest.setAttribute("password", loginDTO.getPassword());
        return "forward:/login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest) {
        LOGGER.info("login username: " + httpServletRequest.getAttribute("username"));
        LOGGER.info("login password: " + httpServletRequest.getAttribute("password"));
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
