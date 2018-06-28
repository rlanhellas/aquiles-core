package br.com.aquiles.security.filter;

import br.com.aquiles.security.dto.AutorizacaoDTO;
import br.com.aquiles.security.dto.Subject;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        Subject subject = null;
        HttpSession sess = ((HttpServletRequest) request).getSession(false);

        if (sess != null) {
            subject = (Subject) sess.getAttribute("subject");
        }

        if (subject == null) {
            String contextPath = ((HttpServletRequest) request)
                    .getContextPath();
            ((HttpServletResponse) response).sendRedirect(contextPath
                    + "/security/index.xhtml");
        } else {

            String requestedUrl = ((HttpServletRequest) request).getServletPath();

            if (requestedUrl.contains("/core/index.xhtml")) {
                chain.doFilter(request, response);
            } else {

                boolean permitir = false;

                for (AutorizacaoDTO auto : subject.getAutorizacao()) {
                    if (!auto.getSgTrn().equalsIgnoreCase("****") &&
                            !auto.getSgDoc().equalsIgnoreCase("****")) {
                        String pathAuto = auto.getSgDoc() + "/" + auto.getSgTrn() + "/";
                        if (requestedUrl.toUpperCase().trim().contains(pathAuto.toUpperCase().trim())) {
                            permitir = true;
                            break;
                        }
                    }
                }

                if (permitir) {
                    chain.doFilter(request, response);
                } else {
                    String contextPath = ((HttpServletRequest) request)
                            .getContextPath();
                    ((HttpServletResponse) response).sendRedirect(contextPath
                            + "/error/acesso_negado.xhtml");
                }
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {

    }

}
