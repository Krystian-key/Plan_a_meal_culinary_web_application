package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession();
        if (sessionUser.getAttribute("Login") == "on") {
            response.sendRedirect(request.getContextPath()+"/app");
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (new AdminDao().validationAdminData(new Admins(email, password))) {
            Admins admins = new AdminDao().readAdminsByEmail(email);
            if(new AdminDao().validationStatus(admins.getId())) {
                HttpSession sessionUser = request.getSession();
                sessionUser.setAttribute("Login", "on");
                sessionUser.setAttribute("adminId", admins.getId());
                sessionUser.setAttribute("nameUser", admins.getFirstName() + " " + admins.getLastName());
                sessionUser.setAttribute("superAdmin", admins.getSuperAdmin());
                sessionUser.setMaxInactiveInterval(60 * 60 * 24);
                response.sendRedirect(request.getContextPath() + "/app");
            }else {
                response.getWriter().println("Jesteś w ope");
            }
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
}
