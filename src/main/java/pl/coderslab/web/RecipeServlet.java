
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
import java.util.List;


@WebServlet(name ="RecipeServlet", urlPatterns ={"/app/recipe/list"})
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
        List<Recipe> allRecipe = new RecipeDao().showAllRecipeUser(adminId);

        request.setAttribute("allRecipes",allRecipe);
        getServletContext().getRequestDispatcher("/app-recipes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

