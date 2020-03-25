package pl.coderslab.web.app.recipe;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteRecipeServlet", urlPatterns = {"/app/recipe/delete"})
public class DeleteRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("recipeId", recipeId);
        request.getRequestDispatcher("/app-remove-recipe.jsp").forward(request, response);

    }

    /**
     * Method doPost for delete recipe
     * Delete recipe only if recipe not included in plan
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int recipeId = Integer.parseInt(request.getParameter("id"));
        List<RecipePlan> recipePlanList = new RecipePlanDao().showAllRecipePlansUser(recipeId);

        if (recipePlanList.isEmpty()) {
            new RecipeDao().deleteRecipe(recipeId);
        }
        response.sendRedirect(request.getContextPath() + "/app/recipe/list");
    }
}
