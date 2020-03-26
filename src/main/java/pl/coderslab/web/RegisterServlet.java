package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "register", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("registration.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        List<String> emails = new AdminDao().readEmails();
        for(String value : emails){
            if(email.equals(value)){
               response.sendRedirect(request.getContextPath()+ "/exception");
               return;
            }
        }

        int superAdmin = 0;
        int enable = 1;
        new AdminDao().createAdmin(new Admins(firstName, lastName, email, password, superAdmin, enable));
        request.getRequestDispatcher("login.html").forward(request, response);
    }
}
