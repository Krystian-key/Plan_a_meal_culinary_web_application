package pl.coderslab.web;

import pl.coderslab.dao.PlanDao;
import pl.coderslab.model.Plan;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "AppRecipeToPlanServlet", urlPatterns = {"/app/recipe/plan/add"})
public class AppRecipeToPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-schedules-meal-recipe.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("kapusta kwas");

    }
}
