package server.crm.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import server.crm.entities.User;
import server.crm.modules.users.services.users.UserService;
import server.crm.services.jwt.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: constructor JWTLoginFilter to begin authenticate
     */
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        System.out.println("URL request login : " + url);
        setAuthenticationManager(authManager);
    }

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: override method type Authentication to authenticate account
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);
        System.out.println();

//        UserDetails userDetails=customUserDetailsService.loadUserByUsername(username);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
    }

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: if authenticate account success will go here.
     * Calling TokenAuthenticationService.addAuthentication to generate token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // Write Authorization to Headers of Response.
        TokenAuthenticationService.addAuthentication(response,authResult);
        System.out.println("*****JWTLoginFilter.successfulAuthentication :" + authResult.getName());

        String authorizationString = response.getHeader("Authorization");
        response.setContentType("application/json");

        System.out.println("Token Authorization String=" + authorizationString);
    }
}
