package pl.coderslab.web.app;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.model.Admins;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditPassword", urlPatterns = {"/app/edit/password"})
public class EditPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/app-edit-password.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
        if (new Admins().checkPassword(oldPassword, new AdminDao().readAdmins(adminId).getPassword())) {
            Admins admins = new Admins();
            admins.setPassword(newPassword);
            admins.setId(adminId);
            new AdminDao().updateAdminPassword(admins);
        }
    }
}
