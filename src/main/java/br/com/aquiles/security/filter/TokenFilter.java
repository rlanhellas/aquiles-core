package br.com.aquiles.security.filter;

import br.com.aquiles.security.util.TokenUtils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import java.io.IOException;

/**
 * Created by Diorge Jorge on 03/11/2016.
 */
@Priority(Priorities.AUTHENTICATION)
public class TokenFilter implements Filter {
    @Inject
    TokenUtils tokenUtils;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        try {
            if (tokenUtils.validateJWT(httpRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
                httpResponse.getWriter().write("Não autorizado");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (final Exception e) {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.getWriter().write("Não autorizado");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }


}
