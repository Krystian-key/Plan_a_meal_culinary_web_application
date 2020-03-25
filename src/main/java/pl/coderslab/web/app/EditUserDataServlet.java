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

@WebServlet(name = "EditUserDataServlet", urlPatterns = {"/app/edit/user"})
public class EditUserDataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession();
        int adminId = Integer.parseInt(sessionUser.getAttribute("adminId").toString());
        request.setAttribute("admin", new AdminDao().readAdmins(adminId));
        request.getRequestDispatcher("/app-edit-user-data.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession();
        int adminId = Integer.parseInt(sessionUser.getAttribute("adminId").toString());
        Admins admins = new Admins();
        admins.setFirstName(request.getParameter("firstName"));
        admins.setLastName(request.getParameter("lastName"));
        admins.setEmail(request.getParameter("email"));
        admins.setId(adminId);
        new AdminDao().updateAdmin(admins);
        response.sendRedirect(request.getContextPath()+"/app");
    }
}
