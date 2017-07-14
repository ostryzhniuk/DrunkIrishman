package andrii.spring;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request,
                          HttpServletResponse response,
                          Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {

        String url;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<>();

        authorities.forEach(authority -> roles.add(authority.getAuthority()));

        if (roles.contains("ADMIN")) {
            url = "/admin";
        } else if (roles.contains("MODERATOR")) {
            url = "/moderator";
        } else if (roles.contains("USER")) {
            url = "/user";
        } else {
            url = "accessDenied";
        }

        return url;
    }

}
