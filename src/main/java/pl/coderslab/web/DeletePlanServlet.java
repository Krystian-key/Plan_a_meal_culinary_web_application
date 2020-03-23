package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipePlanDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeletePlanServlet", urlPatterns = {"/app/plan/delete"})
public class DeletePlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("planId", planId);
        request.getRequestDispatcher("/app-remove-plan.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int planId = Integer.parseInt(request.getParameter("id"));
        new RecipePlanDao().deleteRecipePlanByPlanID(planId);
        new PlanDao().deletePlan(planId);
        response.sendRedirect(request.getContextPath()+"/app/plan/list");
        request.getRequestDispatcher("/app-remove-plan.jsp").forward(request, response);
    }
}
