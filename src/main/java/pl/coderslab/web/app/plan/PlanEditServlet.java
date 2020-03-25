package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DisplayPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PlanEditServlet", urlPatterns = {"/app/plan/edit"})
public class PlanEditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DisplayPlan displayPlan = new RecipePlanDao().detailsPlan(id);
        if(displayPlan.getPlan().getName() != null) {
            request.setAttribute("recipePlanDao", displayPlan);
            request.setAttribute("plan", displayPlan.getPlan());
            request.setAttribute("recipPlanId", id);
        }else {
            Plan recipePlan = new PlanDao().readPlan(id);
            request.setAttribute("plan", recipePlan);
        }
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipe = new ArrayList<>();
        recipe=recipeDao.showAllRecipeUser(adminId);
        request.setAttribute("allRecipe", recipe);

        request.getRequestDispatcher("/app-edit-schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String planName = request.getParameter("planName");
        String planDescription = request.getParameter("planDescription");
        String recipeMonday = request.getParameter("recipeMonday");
        String recipeTuesday = request.getParameter("recipeTuesday");
        String recipeWednesday = request.getParameter("recipeWednesday");
        String recipeThursday = request.getParameter("recipeThursday");
        String recipeFriday = request.getParameter("recipeFriday");
        String recipeSaturday = request.getParameter("recipeSaturday");
        String recipeSunday = request.getParameter("recipeSunday");








    }
}