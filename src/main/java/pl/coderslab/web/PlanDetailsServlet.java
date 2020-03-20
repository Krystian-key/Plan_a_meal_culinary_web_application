
package pl.coderslab.web;

import pl.coderslab.dao.RecipeDao;
import pl.coderslab.dao.RecipePlanDao;
import pl.coderslab.model.DisplayPlan;
import pl.coderslab.model.Recipe;
import pl.coderslab.model.RecipePlan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name ="RecipeDetailsServlet", urlPatterns ={"/app/plan/details"})
public class PlanDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DisplayPlan displayPlan = new RecipePlanDao().detailsPlan(id);
        request.setAttribute("recipePlanDao", displayPlan);
        request.setAttribute("plan", displayPlan.getPlan());
        request.getRequestDispatcher("/app-details-schedules.jsp").forward(request, response);

    }
}

