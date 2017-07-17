package andrii.controllers;

import andrii.dto.LoginDTO;
import andrii.dto.UserDTO;
import andrii.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class DefaultController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authorize")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest) {

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        token.setDetails(new WebAuthenticationDetails(httpServletRequest));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/signUp")
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
