package core.servlets.Seller;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteSubscriptionServlet", urlPatterns = {"/DeleteSubscriptionServlet"})
public class DeleteSubscriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String action = request.getParameter("action");

        if ("show".equals(action)) {
            System.out.println("Showing customer details..."); // Debug
            String[] customerDetails = DatabaseManager.getCustomerDetails(email);
            System.out.println("Customer details fetched."); // Debug

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Delete Subscription</title>");
            out.println("<style>"
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
                    + "    justify-content: space-between;"
                    + "    align-items: center;"
                    + "    padding: 10px 20px;"
                    + "    background-color: rgba(255, 255, 255, 0.8);"
                    + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                    + "    position: absolute;"
                    + "    width: 100%;"
                    + "    left: 0;"
                    + "}"
                    + ".user-name {"
                    + "    font-size: 18px;"
                    + "    font-weight: bold;"
                    + "    color: #4a7ba6;"
                    + "    margin-left: 10px;"
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
                    + "    margin-right: 40px;"
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
                    + "    max-width: 600px;"
                    + "}"
                    + "form {"
                    + "    margin: 20px 0;"
                    + "}"
                    + "label {"
                    + "    display: block;"
                    + "    margin: 10px 0 5px;"
                    + "}"
                    + "input[type=\"email\"] {"
                    + "    padding: 10px;"
                    + "    width: 100%;"
                    + "    border: 1px solid #ccc;"
                    + "    border-radius: 5px;"
                    + "}"
                    + "button {"
                    + "    padding: 10px 15px;"
                    + "    background: #4a7ba6;"
                    + "    color: #fff;"
                    + "    border: none;"
                    + "    border-radius: 5px;"
                    + "    cursor: pointer;"
                    + "}"
                    + "table {"
                    + "    width: 100%;"
                    + "    border-collapse: collapse;"
                    + "    margin-top: 20px;"
                    + "}"
                    + "table, th, td {"
                    + "    border: 1px solid #ddd;"
                    + "}"
                    + "th, td {"
                    + "    padding: 12px;"
                    + "    text-align: left;"
                    + "}"
                    + "th {"
                    + "    background-color: #f4f4f4;"
                    + "}"
                    + ".text-box {"
                    + "    text-align: center;"
                    + "    font-size: 50px;"
                    + "    text-transform: uppercase;"
                    + "    font-weight: bold;"
                    + "    animation: mover 6s linear infinite;"
                    + "    margin-top: 0;"
                    + "    display: flex;"
                    + "    justify-content: center;"
                    + "    color: #cda790;"
                    + "}"
                    + "span {"
                    + "    animation: mover 6s linear infinite;"
                    + "    display: inline-block;"
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
                    + "span:nth-child(1) {"
                    + "    animation-delay: 0.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(2) {"
                    + "    animation-delay: 1s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "span:nth-child(3) {"
                    + "    animation-delay: 1.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(4) {"
                    + "    animation-delay: 2s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "span:nth-child(5) {"
                    + "    animation-delay: 2.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(6) {"
                    + "    animation-delay: 3s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"header\">");
            out.println("<div class=\"user-name\">Όνομα Χρήστη</div>");
            out.println("<a href=\"./logout\" class=\"logout-button\">Log Out</a>");
            out.println("</div>");
            out.println("<div class=\"text-box\">");
            out.println("<span>N</span>");
            out.println("<span>e</span>");
            out.println("<span>t</span>");
            out.println("<span>W</span>");
            out.println("<span>a</span>");
            out.println("<span>y</span>");
            out.println("</div>");
            out.println("<div class=\"container\">");

            if (customerDetails == null) {
                out.println("<p>No data found for email: " + email + "</p>");
            } else {
                out.println("<h2>Customer Details</h2>");
                out.println("<table>");
                out.println("<tr><th>Name</th><td>" + customerDetails[0] + "</td></tr>");
                out.println("<tr><th>Email</th><td>" + customerDetails[1] + "</td></tr>");
                out.println("<tr><th>Phone</th><td>" + customerDetails[2] + "</td></tr>");
                out.println("<tr><th>Debt</th><td>" + customerDetails[3] + "</td></tr>");
                out.println("</table>");
                out.println("<form action=\"DeleteSubscriptionServlet\" method=\"post\">");
                out.println("<input type=\"hidden\" name=\"email\" value=\"" + email + "\">");
                out.println("<input type=\"hidden\" name=\"action\" value=\"delete\">");
                out.println("<button type=\"submit\">Delete Subscription</button>");
                out.println("</form>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            System.out.println("Deleting subscription..."); // Debug
            boolean success = DatabaseManager.deleteCustomerSubscription(email);

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Delete Subscription</title>");
            out.println("<style>"
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
                    + "    justify-content: space-between;"
                    + "    align-items: center;"
                    + "    padding: 10px 20px;"
                    + "    background-color: rgba(255, 255, 255, 0.8);"
                    + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                    + "    position: absolute;"
                    + "    width: 100%;"
                    + "    left: 0;"
                    + "}"
                    + ".user-name {"
                    + "    font-size: 18px;"
                    + "    font-weight: bold;"
                    + "    color: #4a7ba6;"
                    + "    margin-left: 10px;"
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
                    + "    margin-right: 40px;"
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
                    + "    max-width: 600px;"
                    + "}"
                    + "form {"
                    + "    margin: 20px 0;"
                    + "}"
                    + "label {"
                    + "    display: block;"
                    + "    margin: 10px 0 5px;"
                    + "}"
                    + "input[type=\"email\"] {"
                    + "    padding: 10px;"
                    + "    width: 100%;"
                    + "    border: 1px solid #ccc;"
                    + "    border-radius: 5px;"
                    + "}"
                    + "button {"
                    + "    padding: 10px 15px;"
                    + "    background: #4a7ba6;"
                    + "    color: #fff;"
                    + "    border: none;"
                    + "    border-radius: 5px;"
                    + "    cursor: pointer;"
                    + "}"
                    + "table {"
                    + "    width: 100%;"
                    + "    border-collapse: collapse;"
                    + "    margin-top: 20px;"
                    + "}"
                    + "table, th, td {"
                    + "    border: 1px solid #ddd;"
                    + "}"
                    + "th, td {"
                    + "    padding: 12px;"
                    + "    text-align: left;"
                    + "}"
                    + "th {"
                    + "    background-color: #f4f4f4;"
                    + "}"
                    + ".text-box {"
                    + "    text-align: center;"
                    + "    font-size: 50px;"
                    + "    text-transform: uppercase;"
                    + "    font-weight: bold;"
                    + "    animation: mover 6s linear infinite;"
                    + "    margin-top: 0;"
                    + "    display: flex;"
                    + "    justify-content: center;"
                    + "    color: #cda790;"
                    + "}"
                    + "span {"
                    + "    animation: mover 6s linear infinite;"
                    + "    display: inline-block;"
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
                    + "span:nth-child(1) {"
                    + "    animation-delay: 0.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(2) {"
                    + "    animation-delay: 1s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "span:nth-child(3) {"
                    + "    animation-delay: 1.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(4) {"
                    + "    animation-delay: 2s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "span:nth-child(5) {"
                    + "    animation-delay: 2.5s;"
                    + "    color: #457fb1;"
                    + "}"
                    + "span:nth-child(6) {"
                    + "    animation-delay: 3s;"
                    + "    color: #79b4d2;"
                    + "}"
                    + "</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class=\"header\">");
            out.println("<div class=\"user-name\">Όνομα Χρήστη</div>");
            out.println("<a href=\"#\" class=\"logout-button\">Log Out</a>");
            out.println("</div>");
            out.println("<div class=\"text-box\">");
            out.println("<span>N</span>");
            out.println("<span>e</span>");
            out.println("<span>t</span>");
            out.println("<span>W</span>");
            out.println("<span>a</span>");
            out.println("<span>y</span>");
            out.println("</div>");
            out.println("<div class=\"container\">");

            if (success) {
                out.println("<h2>Subscription deleted successfully for email: " + email + "</h2>");
            } else {
                out.println("<h2>Failed to delete subscription for email: " + email + "</h2>");
            }

            out.println("</div>");
            out.println("</body>");
            out.println("</html>");

            out.close();
        }
    }
}
