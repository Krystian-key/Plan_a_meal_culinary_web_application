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
        request.getRequestDispatcher("login.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AdminDao ad = new AdminDao();
        Admins adminLogin = new Admins(email, password);
        if(ad.validationAdminData(adminLogin)){
            HttpSession sess = request.getSession();
            sess.setAttribute("Login", "on");
            sess.setAttribute("adminEmail", adminLogin.getEmail());
            sess.setMaxInactiveInterval(60*60*24);

            request.getRequestDispatcher("home.jsp").forward(request,response);



        }else {

            request.getRequestDispatcher("login.html").forward(request,response);
        }
    }
}
