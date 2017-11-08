package by.iba.electronhandbook.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "mainFilter", urlPatterns = {"/main/*"}, initParams =
            @WebInitParam(name = "env", value = "dev"))

public class MainFilter implements Filter {
    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession(true);
        String[] path = req.getPathInfo().split("/");
        String role = (String) session.getAttribute("role");
        if(role == null){
            role = "admin";
            session.setAttribute("role", role);
        }
        if((path[1].equals("admin") || path[1].equals("getall") || path[1].equals("delete") || path[1].equals("get_required_data") || path[1].equals("add") || path[1].equals("update")) && role.equals("admin"))
            filterChain.doFilter(servletRequest, servletResponse);
        else
            servletRequest.getRequestDispatcher("/templates/pages/error/notFoundError.html").forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        filterConfig= null;
    }
}
