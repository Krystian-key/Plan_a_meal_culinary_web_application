
package pl.coderslab.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet(name ="RecipeServlet", urlPatterns ={"/app/recipe/list"})
public class RecipeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

         String adminIdStr = session.getAttribute("adminId").toString();
         int adminId = Integer.parseInt(adminIdStr);


         request.setAttribute("idAdmin",adminId);

        getServletContext().getRequestDispatcher("/app-recipes.jsp").forward(request, response);
    }
}

