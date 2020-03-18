
package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name ="RecipeDetailsServlet", urlPatterns ={"/app/recipe/details"})
public class RecipeDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int recipeId = Integer.parseInt(session.getAttribute("recipeId").toString());
        Recipe recipe = new RecipeDao().readRecipe(recipeId);
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/app-recipe-details.jsp").forward(request, response);

    }
}

