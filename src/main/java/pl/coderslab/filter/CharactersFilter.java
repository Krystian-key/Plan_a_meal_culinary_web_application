package pl.coderslab.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CharactersFilter", urlPatterns = "/")

public class CharactersFilter implements Filter
{
    //FilterConfig object
    private FilterConfig filterConfig=null;

    //Default constructor
    public CharactersFilter()
    {
        System.out.println("Request response encoder Filter object has been created");
    }

    //Intitialization method
    public void init(FilterConfig filterConfig)
    {
        this.filterConfig=filterConfig;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        //Setting the character set for the request
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // pass the request on
        chain.doFilter(request, response);

        //Setting the character set for the response
        response.setContentType("text/html; charset=UTF-8");
    }

    public void destroy() {
        this.filterConfig=null;
    }
}
