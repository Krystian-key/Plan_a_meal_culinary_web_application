package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AllRecipeServlet", urlPatterns = {"/recipes"})
public class AllRecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipes", new RecipeDao().findAllRecipe());
        request.getRequestDispatcher("/recipes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String status = request.getParameter("recipeName");
        if(!status.equals("")){
            String recipeName = request.getParameter("recipeName");
            request.setAttribute("recipes", new RecipeDao().findRecipesByName(recipeName));
            request.getRequestDispatcher("/recipes.jsp").forward(request, response);
        }else {
            request.setAttribute("recipes", new RecipeDao().findAllRecipe());
            request.getRequestDispatcher("/recipes.jsp").forward(request, response);
        }
    }
}
