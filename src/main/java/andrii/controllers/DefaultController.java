package andrii.controllers;

import andrii.dto.LoginDTO;
import andrii.dto.UserDTO;
import andrii.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class DefaultController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authorize")
    public User login(@RequestBody LoginDTO loginDTO, HttpServletRequest httpServletRequest) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        token.setDetails(new WebAuthenticationDetails(httpServletRequest));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("/currentUser")
    public User currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return new User(authentication.getName(), "", authentication.getAuthorities());
        }
        return (User) authentication.getPrincipal();
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @GetMapping("/userInformation")
    public UserDTO userInformation() {
        return userService.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
