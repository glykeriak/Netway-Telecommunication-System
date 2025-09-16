package core.servlets.Admin;

import core.classes.Packet;
import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewAdminPackets", urlPatterns = {"/adminpackets"})
public class ViewAdminPackets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        out.println("<!DOCTYPE html>");
        out.println("<html lang='el'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>View Packets</title>");
        out.println("<style>"
                + "body {"
                + "background-image: url('backimage.jpg');"
                + "background-size: cover;"
                + "background-position: center;"
                + "background-repeat: no-repeat;"
                + "font-family: Arial, sans-serif;"
                + "margin: 0;"
                + "color: #31393e;"
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
                + "    z-index: 2;"
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
                + ".netway-container {"
                + "    text-align: center;"
                + "    font-size: 50px;"
                + "    text-transform: uppercase;"
                + "    font-weight: bold;"
                + "    animation: mover 6s linear infinite;"
                + "    margin-top: 60px;"
                + "    display: flex;"
                + "    justify-content: center;"
                + "    position: relative;"
                + "    z-index: 1001;"
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
                + ".container {"
                + "background-color: #fff;"
                + "padding: 40px;"
                + "border-radius: 10px;"
                + "box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "text-align: center;"
                + "margin: 0 auto;"
                + "margin-top: 150px;"
                + "max-width: 800px;"
                + "}"
                + "h1 {"
                + "color: #31393e;"
                + "margin-bottom: 30px;"
                + "}"
                + "table {"
                + "font-family: arial, sans-serif;"
                + "border-collapse: collapse;"
                + "width: 100%;"
                + "margin-bottom: 20px;"
                + "}"
                + "td, th {"
                + "border: 1px solid #dddddd;"
                + "text-align: left;"
                + "padding: 8px;"
                + "}"
                + "tr:nth-child(even) {"
                + "background-color: #dddddd;"
                + "}"
                + ".button-container {"
                + "text-align: center;"
                + "}"
                + "a.button {"
                + "width: 150px;"
                + "height: 40px;"
                + "background-color: #457fb1;"
                + "color: #fff;"
                + "border: none;"
                + "border-radius: 5px;"
                + "cursor: pointer;"
                + "font-weight: bold;"
                + "display: inline-block;"
                + "text-align: center;"
                + "line-height: 40px;"
                + "margin: 5px;"
                + "text-decoration: none;"
                + "}"
                + "a.button:hover {"
                + "background-color: #79b4d2;"
                + "}"
                + "</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"header\">");
        out.println("<div class=\"header-left\">");
        out.println("<div id=\"user-name\" class=\"user-name\">" + username + "</div>");
        out.println("<form action=\"show-session\" method=\"post\" style=\"margin: 0;\">");
        out.println("<input type=\"submit\" value=\"Show Session Info\" class=\"session-button\">");
        out.println("</form>");
        out.println("</div>");
        out.println("<a href=\"./logout\" class=\"logout-button\">Log Out</a>");
        out.println("</div>");

        out.println("<div class='text-box'>"
                + "<span>N</span>"
                + "<span>e</span>"
                + "<span>t</span>"
                + "<span>W</span>"
                + "<span>a</span>"
                + "<span>y</span>"
                + "</div>");
        out.println("<div class='container'>");
        out.println("<h1>Packets</h1>");

        List<Packet> listofPackets = DatabaseManager.searchAllPackets();
        out.print("<table>");
        out.print("<tr><th>Packet</th><th>Description</th><th>Price</th><th>Duration</th></tr>");

        for (Packet e : listofPackets) {
            out.print("<tr>"
                    + "<td>" + e.getName() + "</td>"
                    + "<td>" + e.getDescription() + "</td>"
                    + "<td>" + e.getPrice() + "</td>"
                    + "<td>" + e.getDuration() + "</td>"
                    + "</tr>");
        }
        out.print("</table>");
        out.println("<div class='button-container'><a class='button' href='./AdminPage.html'>Go back</a></div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
