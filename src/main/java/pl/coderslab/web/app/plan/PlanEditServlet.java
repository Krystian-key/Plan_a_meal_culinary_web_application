package pl.coderslab.web.app.plan;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DisplayPlan;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

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
        if (displayPlan.getPlan().getName() != null) {
            request.setAttribute("recipePlanDao", displayPlan);
            request.setAttribute("plan", displayPlan.getPlan());
            request.setAttribute("recipPlanId", id);
            HttpSession session = request.getSession();
            int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
            RecipeDao recipeDao = new RecipeDao();
            List<Recipe> recipe = new ArrayList<>();
            recipe = recipeDao.showAllRecipeUser(adminId);
            request.setAttribute("days", new DayNameDao().findAllDayName());
            request.setAttribute("allRecipe", recipe);
        } else {
            Plan recipePlan = new PlanDao().readPlan(id);
            request.setAttribute("plan", recipePlan);

        }

        request.getRequestDispatcher("/app-edit-details-schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] recipeId = request.getParameterValues("selectedRecipeId");
        String[] recipePlanId = request.getParameterValues("selectedRecipePlanId");
        String[] mealName = request.getParameterValues("selectedMeal");
        String[] dayName = request.getParameterValues("selectedDay");

        List <RecipePlan> recipePlanList = new ArrayList<>();
        for(int i=0; i<recipeId.length; i++){
            RecipePlan recipePlan = new RecipePlan();
            recipePlan.setId(Integer.parseInt(recipePlanId[i]));//id for table recipe_plan (recipe_plan.id)
            recipePlan.setRecipeId(Integer.parseInt(recipeId[i])); // recipe_id
            recipePlan.setMealName(mealName[i]); //meal_name
            recipePlan.setDayNameId(new DayNameDao().dayIdToInt(dayName[i])); // day_name_id
            recipePlanList.add(recipePlan);
        }

        RecipePlanDao recipePlanDao = new RecipePlanDao();
        recipePlanDao.updatePlan(recipePlanList);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }
}