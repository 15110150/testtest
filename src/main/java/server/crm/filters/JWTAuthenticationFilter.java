package server.crm.filters;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import server.crm.services.jwt.TokenAuthenticationService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: if go to any URL, method doFilter will call to authenticate token
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("JWTAuthenticationFilter.doFilter");

        Authentication authentication = TokenAuthenticationService
                .getAuthentication((HttpServletRequest) servletRequest);


        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
