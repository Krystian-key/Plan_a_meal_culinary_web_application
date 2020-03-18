
package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name ="RecipeDetailsServlet", urlPatterns ={"/app/recipe/details"})
public class RecipeDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer recipeId = Integer.parseInt(request.getParameter("id"));
        Recipe readRecipe = new RecipeDao().readRecipe(recipeId);
        request.getRequestDispatcher("/app-recipe-details.html").forward(request, response);

    }
}

