package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", urlPatterns = {"/app/recipe/delete"})
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Jestem w GETE");
        request.getRequestDispatcher("/app-remove-recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        new PlanDao().deletePlan(id);
//        response.sendRedirect("/app/plan/list");
        System.out.println("Jestem w POSTE");
        response.sendRedirect(request.getContextPath()+"/app/recipe/list");
//        request.getRequestDispatcher("/app/plan/details").forward(request, response);
    }
}
