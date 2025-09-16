package core.servlets.Seller;

import core.databases.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "InvoiceServlet", urlPatterns = {"/InvoiceServlet"})
public class InvoiceServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        List<String[]> invoiceData = DatabaseManager.getInvoiceData(email);

        String emailCookie = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("emailCookie")) {
                emailCookie = cookie.getValue();
                break;
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Invoice Information</title>");
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
                + "    position: fixed;"
                + "    width: 100%;"
                + "    top: 0;"
                + "    left: 0;"
                + "    z-index: 1000;"
                + "}"
                + ".user-name {"
                + "    font-size: 18px;"
                + "    font-weight: bold;"
                + "    color: #4a7ba6;"
                + "    margin-left: 10px;"
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
                + "    margin-right: 20px;"
                + "}"
                + ".logout-button:hover, .session-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + ".container {"
                + "    background-color: #fff;"
                + "    padding: 40px;"
                + "    border-radius: 10px;"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    text-align: center;"
                + "    margin: 0 auto;"
                + "    margin-top: 150px;"
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
        out.println("<div class=\"header-left\">");
        out.println("<div class=\"user-name\">" + (emailCookie != null ? "Welcome, " + emailCookie : "Welcome") + "</div>");
        out.println("<form action=\"show-session\" method=\"post\" style=\"margin: 0;\">");
        out.println("<input type=\"submit\" value=\"Show Session Info\" class=\"session-button\">");
        out.println("</form>");
        out.println("</div>");
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
        out.println("<h2>Invoice Information</h2>");

        if (invoiceData.isEmpty()) {
            out.println("<p>No data found for email: " + email + "</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Phone Number</th><th>Charge</th></tr>");
            for (String[] row : invoiceData) {
                out.println("<tr><td>" + row[0] + "</td><td>" + row[1] + "</td></tr>");
            }
            out.println("</table>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
