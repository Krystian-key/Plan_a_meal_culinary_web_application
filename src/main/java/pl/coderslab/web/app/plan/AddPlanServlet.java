package pl.coderslab.web.app.plan;

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

@WebServlet(name = "AddPlanServlet", urlPatterns = {"/app/plan/add"})
public class AddPlanServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/app-add-schedules.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("planDescription");
        HttpSession sessionId = request.getSession();
        int adminId = Integer.parseInt(String.valueOf(sessionId.getAttribute("adminId")));
        Plan plan = new Plan(name, description, LocalDateTime.now(), adminId);
        new PlanDao().createPlan(plan);
        response.sendRedirect(request.getContextPath() + "/app/plan/list");
    }
}
