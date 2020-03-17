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
            // separation from whole url
            String[] list = req.getRequestURI().split("/");
            // Getting name from page
            String page = null;
            if (list[list.length - 1].indexOf(".jsp") > 0) {
                page = list[list.length - 1];
            }
            // If oppening page "recipes" then do check
            if ((page != null) && page.equalsIgnoreCase("recipes.html")) {
                // If before was open "login.html" then will open "recipes.html"
                if (pages.contains("login.html") || page.contains("login") ) {
                    filterChain.doFilter(request, response);
                    return;
                } else {
                    // Redirect to "login.html"
                    ServletContext ctx = filterConfig.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/login.html");
                    dispatcher.forward(request, response);
                    return;
                }
            } else if (page != null) {
                // Add page to list
                if (!pages.contains(page))
                    pages.add(page);
            }
        }
        filterChain.doFilter(request, response);
    }
}