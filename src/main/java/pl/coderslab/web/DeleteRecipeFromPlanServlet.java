package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeFromPlan", urlPatterns = {"/app/plan/delete-recipe"})
public class DeleteRecipeFromPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Jestem w GETE");
        request.getRequestDispatcher("/app-remove-recipe-from-plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        new PlanDao().deletePlan(id);
//        response.sendRedirect("/app/plan/list");
        System.out.println("Jestem w POSTE");
        response.sendRedirect(request.getContextPath()+"/app/plan/details");
//        request.getRequestDispatcher("/app/plan/details").forward(request, response);
    }
}
