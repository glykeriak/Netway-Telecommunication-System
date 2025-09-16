package core.servlets.Admin;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SearchSellerServlet", urlPatterns = {"/searchseller"})
public class SearchSellerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        ResultSet sellerData = DatabaseManager.searchSellerByEmail(email);

        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html lang=\"en\">");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset=\"UTF-8\">");
        response.getWriter().println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        response.getWriter().println("<title>Search Seller</title>");
        response.getWriter().println("<style>"
                + "body {"
                + "    background-image: url('backimage.jpg');"
                + "    background-size: cover;"
                + "    background-position: center;"
                + "    font-family: Arial, sans-serif;"
                + "    margin: 0;"
                + "    padding: 0;"
                + "}"
                + ".header {"
                + "    display: flex;"
                + "    justify-content: flex-end;"
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
                + ".logout-button {"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    border: none;"
                + "    border-radius: 5px;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    padding: 10px 20px;"
                + "    text-decoration: none;"
                + "    transition: background-color 0.3s;"
                + "    margin-right: 20px;"
                + "}"
                + ".logout-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + ".container {"
                + "    background-color: #fff;"
                + "    padding: 40px;"
                + "    border-radius: 10px;"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    text-align: center;"
                + "    margin: 0 auto;"
                + "    margin-top: 100px;"
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
            if (sellerData != null && sellerData.next()) {
                response.getWriter().println("<h2>Seller Found</h2>");
                response.getWriter().println("<table>");
                response.getWriter().println("<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th></tr>");
                do {
                    String userId = sellerData.getString("U_ID");
                    String firstName = sellerData.getString("firstname");
                    String lastName = sellerData.getString("lastname");
                    response.getWriter().println("<tr><td>" + userId + "</td><td>" + firstName + "</td><td>" + lastName + "</td><td>" + email + "</td></tr>");
                } while (sellerData.next());
                response.getWriter().println("</table>");
                response.getWriter().println("<a href=\"AdminPage.html\" class=\"back-button\">Go Back</a>");
            } else {
                response.getWriter().println("<h2>No Seller Found</h2>");
                response.getWriter().println("<a href=\"AdminPage.html\" class=\"back-button\">Go Back</a>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error occurred while searching for the seller</h2>");
            response.getWriter().println("<a href=\"AdminPage.html\" class=\"back-button\">Go Back</a>");
        } finally {
            try {
                if (sellerData != null) {
                    sellerData.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        response.getWriter().println("</div>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
