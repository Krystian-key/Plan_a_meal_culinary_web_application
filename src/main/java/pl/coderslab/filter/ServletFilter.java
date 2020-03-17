package pl.coderslab.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/ServletFilter")
public class ServletFilter implements Filter {
    private FilterConfig filterConfig;
    private static ArrayList<String> pages;

    public ServletFilter() {
        if (pages == null)
            pages = new ArrayList<String>();
    }

    /**
     * Method cleanig resources
     *
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * Method initilization filter
     *
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        filterConfig = fConfig;
    }

    /**
     * Method filter
     *
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        // If filter is active, then do check
        if (filterConfig.getInitParameter("active").equalsIgnoreCase("true")) {
            HttpServletRequest req = (HttpServletRequest) request;

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            HttpSession sessionUser = req.getSession();
            if (sessionUser.getAttribute("user") == null && req.getRequestURI().endsWith("recipes.html")) {
                System.out.println(sessionUser.getAttribute("user"));
                RequestDispatcher rd = request.getRequestDispatcher("/login.html");
                rd.forward(request, response);
            } else {
                ServletContext ctx = filterConfig.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/recipes.html");
                dispatcher.forward(request, response);
                return;
            }
        }
            filterChain.doFilter(request, response);
        }
}