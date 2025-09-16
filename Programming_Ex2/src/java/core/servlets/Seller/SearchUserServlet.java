package core.servlets.Seller;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SearchUserServlet", urlPatterns = {"/searchuser"})
public class SearchUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        ResultSet userData = DatabaseManager.searchUserByEmail(email);

        String emailCookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("emailCookie")) {
                emailCookie = cookie.getValue();
            }
        }

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang=\"en\">");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset=\"UTF-8\">");
        response.getWriter().println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        response.getWriter().println("<title>Search User</title>");
        response.getWriter().println("<style>"
                + "body {"
                + "    background-image: url('backimage.jpg');"
                + "    background-size: cover;"
                + "    background-position: center;"
                + "    font-family: Arial, sans-serif;"
                + "    margin: 0;"
                + "}"
                + ".header {"
                + "    display: flex;"
                + "    justify-content: space-between;"
                + "    align-items: center;"
                + "    padding: 10px 20px;"
                + "    background-color: rgba(255, 255, 255, 0.8);"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    position: fixed;"
                + "    width: 100%;"
                + "    top: 0;"
                + "    left: 0;"
                + "    z-index: 1000;"
                + "}"
                + ".header-left {"
                + "    display: flex;"
                + "    flex-direction: column;"
                + "}"
                + ".header-buttons {"
                + "    display: flex;"
                + "    gap: 10px;"
                + "}"
                + ".logout-button, .session-button {"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    border: none;"
                + "    border-radius: 5px;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    padding: 10px 20px;"
                + "    text-decoration: none;"
                + "    transition: background-color 0.3s;"
                + "}"
                + ".logout-button:hover, .session-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + ".user-name {"
                + "    font-size: 18px;"
                + "    font-weight: bold;"
                + "    color: #4a7ba6;"
                + "    margin-bottom: 10px;"
                + "}"
                + ".container {"
                + "    background-color: #fff;"
                + "    padding: 40px;"
                + "    border-radius: 10px;"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    text-align: center;"
                + "    margin: 0 auto;"
                + "    margin-top: 150px;"
                + "    max-width: 800px;"
                + "}"
                + ".netway-container {"
                + "    text-align: center;"
                + "    font-size: 50px;"
                + "    text-transform: uppercase;"
                + "    font-weight: bold;"
                + "    animation: mover 6s linear infinite;"
                + "    margin-top: 60px;"
                + "    display: flex;"
                + "    justify-content: center;"
                + "}"
                + ".netway-container span {"
                + "    animation: mover 6s linear infinite;"
                + "    display: inline-block;"
                + "    color: #4a7ba6;"
                + "    font-weight: bolder;"
                + "}"
                + "@keyframes mover {"
                + "    0% {"
                + "        text-shadow: 0 0 30px rgba(0, 0, 0, 0);"
                + "    }"
                + "    50% {"
                + "        text-shadow: 0 0 30px rgba(0, 0, 0, 0.30);"
                + "        transform: translateY(30px);"
                + "    }"
                + "    100% {"
                + "        text-shadow: 0 0 30px rgba(0, 0, 0, 0);"
                + "    }"
                + "}"
                + ".netway-container span:nth-child(1) {"
                + "    animation-delay: 0.5s;"
                + "    color: #457fb1;"
                + "}"
                + ".netway-container span:nth-child(2) {"
                + "    animation-delay: 1s;"
                + "    color: #79b4d2;"
                + "}"
                + ".netway-container span:nth-child(3) {"
                + "    animation-delay: 1.5s;"
                + "    color: #457fb1;"
                + "}"
                + ".netway-container span:nth-child(4) {"
                + "    animation-delay: 2s;"
                + "    color: #79b4d2;"
                + "}"
                + ".netway-container span:nth-child(5) {"
                + "    animation-delay: 2.5s;"
                + "    color: #457fb1;"
                + "}"
                + ".netway-container span:nth-child(6) {"
                + "    animation-delay: 3s;"
                + "    color: #79b4d2;"
                + "}"
                + "h2 {"
                + "    color: #4a7ba6;"
                + "    margin-bottom: 30px;"
                + "}"
                + "label {"
                + "    display: block;"
                + "    margin-bottom: 10px;"
                + "    color: #4a7ba6;"
                + "    font-weight: bold;"
                + "}"
                + "input[type=\"email\"] {"
                + "    width: calc(100% - 22px);"
                + "    height: 30px;"
                + "    border-radius: 5px;"
                + "    border: 1px solid #4a7ba6;"
                + "    padding: 5px;"
                + "    margin-bottom: 15px;"
                + "}"
                + "input[type=\"submit\"] {"
                + "    width: 100%;"
                + "    height: 40px;"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    border: none;"
                + "    border-radius: 5px;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    margin: 5px;"
                + "}"
                + "input[type=\"submit\"]:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + ".button-container {"
                + "    display: flex;"
                + "    justify-content: center;"
                + "    margin-top: 20px;"
                + "}"
                + "table {"
                + "    width: 100%;"
                + "    border-collapse: collapse;"
                + "    margin-bottom: 20px;"
                + "}"
                + "table, th, td {"
                + "    border: 1px solid #4a7ba6;"
                + "    padding: 10px;"
                + "}"
                + "th {"
                + "    background-color: #4a7ba6;"
                + "    color: white;"
                + "}"
                + "td {"
                + "    text-align: left;"
                + "}"
                + ".back-button {"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    border: none;"
                + "    border-radius: 5px;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    padding: 10px 20px;"
                + "    text-decoration: none;"
                + "    transition: background-color 0.3s;"
                + "    margin-top: 20px;"
                + "}"
                + ".back-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + "</style>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<div class=\"header\">");
        response.getWriter().println("<div class=\"header-left\">");
        response.getWriter().println("<div class=\"user-name\">" + emailCookie + "</div>");
        response.getWriter().println("<form action=\"show-session\" method=\"post\" style=\"margin: 0;\">");
        response.getWriter().println("<input type=\"submit\" value=\"Show Session Info\" class=\"session-button\">");
        response.getWriter().println("</form>");
        response.getWriter().println("</div>");
        response.getWriter().println("<a href=\"./logout\" class=\"logout-button\">Log Out</a>");
        response.getWriter().println("</div>");
        response.getWriter().println("<div class=\"netway-container\">");
        response.getWriter().println("<span>N</span>");
        response.getWriter().println("<span>e</span>");
        response.getWriter().println("<span>t</span>");
        response.getWriter().println("<span>W</span>");
        response.getWriter().println("<span>a</span>");
        response.getWriter().println("<span>y</span>");
        response.getWriter().println("</div>");
        response.getWriter().println("<div class=\"container\">");

        try {
            if (userData != null && userData.next()) {
                response.getWriter().println("<h2>Your Profile</h2>");
                response.getWriter().println("<table>");
                response.getWriter().println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>");
                do {
                    String userId = userData.getString("U_ID");
                    String firstName = userData.getString("firstname");
                    String lastName = userData.getString("lastname");
                    response.getWriter().println("<tr><td>" + userId + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + email + "</td></tr>");
                } while (userData.next());
                response.getWriter().println("</table>");
            } else {
                response.getWriter().println("<h2>No User Found</h2>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error occurred while searching for the user</h2>");
        } finally {
            try {
                if (userData != null) {
                    userData.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.getWriter().println("<a href=\"SellerPage.html\" class=\"back-button\">Go Back</a>");
        response.getWriter().println("</div>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
