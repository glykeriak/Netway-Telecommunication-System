package core.servlets.Seller;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LinkProcess", urlPatterns = {"/link"})
public class LinkProcess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String phoneNumber = request.getParameter("phoneNumber");
        String packet_name = request.getParameter("programType");

        int status = DatabaseManager.linkPhoneWithPacket(phoneNumber, packet_name);

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Link Process Result</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    background-image: url('backimage.jpg');");
        out.println("    background-size: cover;");
        out.println("    background-position: center;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 0;");
        out.println("}");
        out.println(".header {");
        out.println("    display: flex;");
        out.println("    justify-content: flex-end;");
        out.println("    align-items: center;");
        out.println("    padding: 10px 20px;");
        out.println("    background-color: rgba(255, 255, 255, 0.8);");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);");
        out.println("    position: fixed;");
        out.println("    width: 100%;");
        out.println("    top: 0;");
        out.println("    left: 0;");
        out.println("    z-index: 1000;");
        out.println("}");
        out.println(".logout-button {");
        out.println("    background-color: #4a7ba6;");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    font-weight: bold;");
        out.println("    padding: 10px 20px;");
        out.println("    text-decoration: none;");
        out.println("    transition: background-color 0.3s;");
        out.println("    margin-right: 20px;");
        out.println("}");
        out.println(".logout-button:hover {");
        out.println("    background-color: #bc85a3;");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: #fff;");
        out.println("    padding: 40px;");
        out.println("    border-radius: 10px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);");
        out.println("    text-align: center;");
        out.println("    margin: 0 auto;");
        out.println("    margin-top: 150px;");
        out.println("    max-width: 800px;");
        out.println("}");
        out.println(".netway-container {");
        out.println("    text-align: center;");
        out.println("    font-size: 50px;");
        out.println("    text-transform: uppercase;");
        out.println("    font-weight: bold;");
        out.println("    animation: mover 6s linear infinite;");
        out.println("    margin-top: 60px;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("}");
        out.println(".netway-container span {");
        out.println("    animation: mover 6s linear infinite;");
        out.println("    display: inline-block;");
        out.println("    color: #4a7ba6;");
        out.println("    font-weight: bolder;");
        out.println("}");
        out.println("@keyframes mover {");
        out.println("    0% {");
        out.println("        text-shadow: 0 0 30px rgba(0, 0, 0, 0);");
        out.println("    }");
        out.println("    50% {");
        out.println("        text-shadow: 0 0 30px rgba(0, 0, 0, 0.30);");
        out.println("        transform: translateY(30px);");
        out.println("    }");
        out.println("    100% {");
        out.println("        text-shadow: 0 0 30px rgba(0, 0, 0, 0);");
        out.println("    }");
        out.println("}");
        out.println(".netway-container span:nth-child(1) {");
        out.println("    animation-delay: 0.5s;");
        out.println("    color: #457fb1;");
        out.println("}");
        out.println(".netway-container span:nth-child(2) {");
        out.println("    animation-delay: 1s;");
        out.println("    color: #79b4d2;");
        out.println("}");
        out.println(".netway-container span:nth-child(3) {");
        out.println("    animation-delay: 1.5s;");
        out.println("    color: #457fb1;");
        out.println("}");
        out.println(".netway-container span:nth-child(4) {");
        out.println("    animation-delay: 2s;");
        out.println("    color: #79b4d2;");
        out.println("}");
        out.println(".netway-container span:nth-child(5) {");
        out.println("    animation-delay: 2.5s;");
        out.println("    color: #457fb1;");
        out.println("}");
        out.println(".netway-container span:nth-child(6) {");
        out.println("    animation-delay: 3s;");
        out.println("    color: #79b4d2;");
        out.println("}");
        out.println(".back-button {");
        out.println("    background-color: #4a7ba6;");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    font-weight: bold;");
        out.println("    padding: 10px 20px;");
        out.println("    text-decoration: none;");
        out.println("    transition: background-color 0.3s;");
        out.println("    margin-top: 20px;");
        out.println("}");
        out.println(".back-button:hover {");
        out.println("    background-color: #bc85a3;");
        out.println("}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"header\">");
        out.println("<a href=\"./logout\" class=\"logout-button\">Log Out</a>");
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

        if (status > 0) {
            out.println("<h2>Linking successful!</h2>");
        } else {
            out.println("<h2>Linking failed. Please try again.</h2>");
        }

        out.println("<a href=\"SellerPage.html\" class=\"back-button\">Go Back</a>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
