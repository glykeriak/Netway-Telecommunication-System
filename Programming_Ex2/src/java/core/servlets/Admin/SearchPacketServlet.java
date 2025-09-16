package core.servlets.Admin;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "SearchPacketServlet", urlPatterns = {"/SearchPacketServlet"})
public class SearchPacketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String packetName = request.getParameter("packetName");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Search Packet</title>");
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
                + "    background-color: #fff;"
                + "    padding: 40px;"
                + "    border-radius: 10px;"
                + "    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);"
                + "    text-align: center;"
                + "    margin: 0 auto;"
                + "    margin-top: 50px;"
                + "    max-width: 400px;"
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
                + "input[type='text'], input[type='submit'], input[type='number'] {"
                + "    width: calc(100% - 22px);"
                + "    height: 30px;"
                + "    border-radius: 5px;"
                + "    border: 1px solid #4a7ba6;"
                + "    padding: 5px;"
                + "    margin-bottom: 15px;"
                + "}"
                + "input[type='submit'] {"
                + "    background-color: #4a7ba6;"
                + "    color: #fff;"
                + "    cursor: pointer;"
                + "    font-weight: bold;"
                + "    transition: background-color 0.3s;"
                + "}"
                + "input[type='submit']:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + ".back-button {"
                + "    width: calc(100% - 22px);"
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
                + "    display: block;"
                + "}"
                + ".back-button:hover {"
                + "    background-color: #bc85a3;"
                + "}"
                + "</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"header\">");
        out.println("<a href='./logout' class='logout-button'>Log Out</a>");
        out.println("</div>");
        out.println("<div class=\"netway-container\">");
        out.println("<span>N</span>");
        out.println("<span>e</span>");
        out.println("<span>t</span>");
        out.println("<span>W</span>");
        out.println("<span>a</span>");
        out.println("<span>y</span>");
        out.println("</div>");
        out.println("<div class=\"container\">");

        if (packetName == null || packetName.trim().isEmpty()) {
            out.println("<h2>Please enter a packet name.</h2>");
            out.println("<a href='SearchPacket.html' class='back-button'>Go Back</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        try (Connection conn = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM Packet WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, packetName);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Packet found, display form to edit packet details
                        out.println("<form action='./UpdatePacketServlet' method='POST'>");
                        out.println("<label for='packetName'>Packet Name:</label>");
                        out.println("<input type='text' id='packetName' name='packetName' value='" + rs.getString("name") + "' readonly><br><br>");
                        out.println("<label for='description'>Description:</label>");
                        out.println("<input type='text' id='description' name='description' value='" + rs.getString("description") + "'><br><br>");
                        out.println("<label for='price'>Price:</label>");
                        out.println("<input type='number' id='price' name='price' value='" + rs.getString("price") + "' step='0.01'><br><br>");
                        out.println("<label for='duration'>Duration (days):</label>");
                        out.println("<input type='number' id='duration' name='duration' value='" + rs.getString("duration") + "' min='1' max='90'><br><br>");
                        out.println("<input type='submit' name='action' value='Update Packet'>");
                        out.println("<input type='submit' name='action' value='Delete Packet' formnovalidate>");
                        out.println("</form>");
                    } else {
                        // Packet not found
                        out.println("<h2>No packet found with the name: " + packetName + "</h2>");
                    }
                }
            }
        } catch (SQLException e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }

        out.println("<a href='SearchPacket.html' class='back-button'>Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
