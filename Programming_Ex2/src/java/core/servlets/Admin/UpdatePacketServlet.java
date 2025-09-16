package core.servlets.Admin;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdatePacketServlet", urlPatterns = {"/UpdatePacketServlet"})
public class UpdatePacketServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String packetName = request.getParameter("packetName");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Update Packet</title>");
        out.println("<style>"
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
                + "h2 {"
                + "    color: #4a7ba6;"
                + "    margin-bottom: 30px;"
                + "}"
                + "</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container\">");

        if ("Delete Packet".equals(action)) {
            boolean success = DatabaseManager.deletePacket(packetName);
            if (success) {
                out.println("<h2>Packet deleted successfully!</h2>");
            } else {
                out.println("<h2>Failed to delete packet.</h2>");
            }
        } else {
            String description = request.getParameter("description");
            String priceStr = request.getParameter("price");
            String durationStr = request.getParameter("duration");

            double price = Double.parseDouble(priceStr);
            int duration = Integer.parseInt(durationStr);

            // Validate duration
            if (duration <= 0 || duration > 90) {
                out.println("<h2>Error: Duration must be a positive integer not exceeding 90 days.</h2>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
                return;
            }

            boolean success = DatabaseManager.updatePacket(packetName, description, price, duration);
            if (success) {
                out.println("<h2>Packet updated successfully!</h2>");
            } else {
                out.println("<h2>Failed to update packet.</h2>");
            }
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }
}
