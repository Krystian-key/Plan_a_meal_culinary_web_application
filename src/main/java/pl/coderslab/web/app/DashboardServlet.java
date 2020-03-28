package pl.coderslab.web.app;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DisplayPlan;
import pl.coderslab.model.Plan;

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
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
        request.setAttribute("numberRecipe", new RecipeDao().quantityRecipeUser(adminId));
        request.setAttribute("numberPlan", new PlanDao().quantityPlanUser(adminId));
        RecipePlanDao recipePlanDao = new RecipePlanDao();
        DisplayPlan lastAddedPlan = recipePlanDao.detailsPlan(adminId, recipePlanDao.choseQuery(2));
        if (lastAddedPlan.getPlan().getName() != null) {
            request.setAttribute("lastAddedPlan", lastAddedPlan);
            request.setAttribute("plan", lastAddedPlan.getPlan());
        } else {
            Plan plan = new PlanDao().readLastAddedPlan(adminId);
            request.setAttribute("plan", plan);
        }
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}