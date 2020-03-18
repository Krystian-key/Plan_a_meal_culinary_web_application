package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/app"})
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String adminIdStr = session.getAttribute("adminId").toString();
        int adminId = Integer.parseInt(adminIdStr);

        int quantityRecipe = new RecipeDao().allRecipeFromLoginUser(adminId);
        request.setAttribute("numberRecipe",quantityRecipe);



        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}