package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;
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
        int recipPlanId = Integer.parseInt(request.getParameter("recipPlanId"));
        int planId = Integer.parseInt(request.getParameter("planId"));
        request.setAttribute("delateRecipPlanId", recipPlanId);
        request.setAttribute("deletePlanId", planId);
        request.getRequestDispatcher("/app-remove-recipe-from-plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipPlanId = Integer.parseInt(request.getParameter("delateRecipPlanId"));
        int planId = Integer.parseInt(request.getParameter("deletePlanId"));
        new RecipePlanDao().deleteRecipePlan(planId);
        response.sendRedirect(request.getContextPath()+"/app/plan/details?id="+recipPlanId);
    }
}
