package core.servlets.Admin;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "AddSellerServlet", urlPatterns = {"/AddSellerServlet"})
public class AddSellerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailCookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("emailCookie")) {
                emailCookie = cookie.getValue();
            }
        }
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String afm = request.getParameter("afm");
        String phone = request.getParameter("phone");
        String code = request.getParameter("code");

        boolean success = DatabaseManager.addSeller(firstname, lastname, email, afm, phone, code, emailCookie);

        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang=\"en\">");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset=\"UTF-8\">");
        response.getWriter().println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        response.getWriter().println("<title>Register Seller</title>");
        response.getWriter().println("<style>"
                + "body {"
                + "    background-image: url('backimage.jpg');"
                + "    background-size: cover;"
                + "    background-position: center;"
                + "    font-family: Arial, sans-serif;"
                + "    margin: 0;"
                + "    padding: 0;"
                + "}"
                + ".container {"
                + "    background-color: #fff;"
                + "    padding: 40px;"
                + "    border-radius: 10px;"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    text-align: center;"
                + "    margin: 0 auto;"
                + "    margin-top: 50px;"
                + "    max-width: 400px;"
                + "}"
                + ".back-button {"
                + "    width: 250px;"
                + "    height: 40px;"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    border: none;"
                + "    border-radius: 5px;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    margin-top: 20px;"
                + "    text-decoration: none;"
                + "    line-height: 40px;"
                + "    transition: background-color 0.3s;"
                + "    display: inline-block;"
                + "}"
                + ".back-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + "h2 {"
                + "    color: #4a7ba6;"
                + "    margin-bottom: 30px;"
                + "}"
                + "</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class=\"container\">");

        if (success) {
            response.getWriter().println("<h2>Seller registered successfully!</h2>");
        } else {
            response.getWriter().println("<h2>Failed to register seller.</h2>");
        }

        response.getWriter().println("<a href=\"AdminPage.html\" class=\"back-button\">Go Back</a>");
        response.getWriter().println("</div>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
