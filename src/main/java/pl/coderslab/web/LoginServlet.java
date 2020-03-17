package pl.coderslab.web;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;
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
        if(new AdminDao().validationAdminData(new Admins(email, password))){
            int id = new AdminDao().readAdminsByEmail(email).getId();
            HttpSession sess = request.getSession();
            sess.setAttribute("Login", "on");
            sess.setAttribute("adminId", id);
            sess.setAttribute("count", new PlanDao().showAllPlansUser(id).size());
            sess.setMaxInactiveInterval(60*60*24);
            request.getRequestDispatcher("home.jsp").forward(request,response);
        }else {
            request.getRequestDispatcher("login.html").forward(request,response);
        }
    }
}
