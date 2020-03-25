package pl.coderslab.web.app.recipe;

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

@WebServlet(name = "EditRecipeServlet", urlPatterns = {"/app/edit/recipe"})
public class EditRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        RecipeDao recipeDao = new RecipeDao();
        Recipe recipe = new Recipe();
        recipe= recipeDao.readRecipe(Integer.parseInt(id));
        request.setAttribute("recipe", recipe);
        getServletContext().getRequestDispatcher("/app-recipe-edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String recipeName = request.getParameter("recipeName");
        String recipeDescripiton = request.getParameter("recipeDescripiton");
        String recipePreparationTime = request.getParameter("recipePreparationTime");
        String recipePreparation = request.getParameter("recipePreparation");
        String recipeIngredients = request.getParameter("recipeIngredients");
        LocalDateTime update = LocalDateTime.now();

        RecipeDao recipeDaoDao = new RecipeDao();
        Recipe oldRecipe = recipeDaoDao.readRecipe(Integer.parseInt(id));
        if(recipeName.equals("")){
            recipeName=oldRecipe.getName();
        }if(recipeDescripiton.equals("")){
            recipeDescripiton=oldRecipe.getDescription();
        }if(recipePreparationTime.equals("")){
            recipePreparationTime=Integer.toString(oldRecipe.getPreparationTime());
        }if(recipePreparation.equals("")){
            recipePreparation=oldRecipe.getPreparation();
        }if(recipeIngredients.equals("")){
            recipeIngredients=oldRecipe.getIngredients();
        }



        Recipe recipe = new Recipe();
        recipe.setName(recipeName);
        recipe.setDescription(recipeDescripiton);
        recipe.setPreparationTime(Integer.parseInt(recipePreparationTime));
        recipe.setPreparation(recipePreparation);
        recipe.setIngredients(recipeIngredients);
        recipe.setUpdated(update);
        recipe.setId(Integer.parseInt(id));
        RecipeDao recipeDao = new RecipeDao();
        recipeDao.updateRecipe(recipe);
        response.sendRedirect(request.getContextPath()+"/app/recipe/list");
    }
}
