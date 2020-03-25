package pl.coderslab.web.app.plan;

import pl.coderslab.dao.DayNameDao;
import pl.coderslab.dao.PlanDao;
import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DayName;
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
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "AppRecipeToPlanServlet", urlPatterns = {"/app/recipe/plan/add"})
public class AppRecipeToPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(session.getAttribute("adminId")));
        PlanDao planDao = new PlanDao();
        List<Plan> list = planDao.showAllPlansUser(adminId);
        request.setAttribute("planList",list);

        RecipeDao recipeDao = new RecipeDao();
        List <Recipe> recipeList = recipeDao.showAllRecipeUser(adminId);
        request.setAttribute("recipeList",recipeList);

        DayNameDao dayNameDao = new DayNameDao();
        List <DayName> dayNameList = dayNameDao.findAllDayName();
        request.setAttribute("dayNameList", dayNameList);


        getServletContext().getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String choosePlan = request.getParameter("planList");
        String meal = request.getParameter("meal");
        String recipieList = request.getParameter("recipie");
        String dayNameList = request.getParameter("day");

        RecipePlanDao recipePlanDao = new RecipePlanDao();

        RecipePlan recipePlan = new RecipePlan();
        recipePlan.setPlanId(Integer.parseInt(choosePlan));
        recipePlan.setMealName(meal);
        recipePlan.setRecipeId(Integer.parseInt(recipieList));
        recipePlan.setDayNameId(Integer.parseInt(dayNameList));
        recipePlan.setDisplayOrder(1);
        recipePlanDao.createPlan(recipePlan);

        response.sendRedirect(request.getContextPath()+"/app/recipe/plan/add");


    }
}
