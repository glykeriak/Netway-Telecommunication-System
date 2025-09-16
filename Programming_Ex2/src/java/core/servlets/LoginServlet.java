package core.servlets;

import core.databases.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginSeller", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int status = DatabaseManager.login(email, password);
        System.out.println("Login status: " + status);  // Debug message

        if (status > 0) {
            Cookie emailCookie = new Cookie("emailCookie", email);
            emailCookie.setMaxAge(36000);
            response.addCookie(emailCookie);

            HttpSession session = request.getSession();
            session.setAttribute("username", email);

            switch (status) {
                case 1:
                    System.out.println("Redirecting to ClientPage.html");  // Debug message
                    response.sendRedirect("./ClientPage.html");
                    break;
                case 2:
                    String sellerPagePath = getServletContext().getRealPath("/SellerPage.html");
                    System.out.println("SellerPage.html path: " + sellerPagePath);  // Debug message
                    response.sendRedirect(request.getContextPath() + "/SellerPage.html");
                    break;
                case 3:
                    System.out.println("Redirecting to AdminPage.html");  // Debug message
                    response.sendRedirect("./AdminPage.html");
                    break;
            }
        } else {
            System.out.println("Login failed, redirecting to index.html");  // Debug message
            request.getRequestDispatcher("./index.html").include(request, response);
        }
        out.close();
    }
}
