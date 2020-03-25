package pl.coderslab.web.app.plan;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        } else {
            Plan recipePlan = new PlanDao().readPlan(id);
            request.setAttribute("plan", recipePlan);
        }
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        RecipeDao recipeDao = new RecipeDao();
        List<Recipe> recipe = new ArrayList<>();
        recipe = recipeDao.showAllRecipeUser(adminId);
        request.setAttribute("allRecipe", recipe);
        request.getRequestDispatcher("/app-details-schedules-test.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] recipeId = request.getParameterValues("recipeTuesdayId");
        String[] mealName = request.getParameterValues("meal");
        String[] dayName = request.getParameterValues("day");
        String[] recipePlanIds = request.getParameterValues("recipePlanId");
        String[] planId = request.getParameterValues("planId");


        for (String value : recipeId) {
            response.getWriter().println("recipeId = "+value + "<br>");
        }
        for (String valueSec : mealName) {
            response.getWriter().println("mealId = "+valueSec + "<br>");
        }
        for (String value : dayName) {
            response.getWriter().println("day = "+value + "<br>");
        }
        for (String value : recipePlanIds) {
            response.getWriter().println("recipePlanId = "+value + "<br>");
        }

        for (String value : planId) {
            response.getWriter().println("planId = "+value + "<br>");
        }
 //       Map<String,List<RecipePlan>> planDetails = new HashMap<>();
        List <RecipePlan> recipePlanList = new ArrayList<>();
        for(int i=0; i<recipeId.length -1; i++){

            RecipePlan recipePlan = new RecipePlan();
            recipePlan.setId(Integer.parseInt(recipePlanIds[i]));//id for table recipe_plan
            recipePlan.setRecipeId(Integer.parseInt(recipeId[i])); // recipe_id
            recipePlan.setMealName(mealName[i]); //meal_name
            recipePlan.setDayNameId(Integer.parseInt(dayName[0])); // day_name_id
            recipePlan.setPlanId(Integer.parseInt(planId[i])); // plan_id
            recipePlanList.add(recipePlan);



        }

    }
}