package core.servlets.Seller;

import core.databases.DatabaseManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@WebServlet(name = "AddClient", urlPatterns = {"/addclient"})
public class AddClient extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        if (email == null || email.isEmpty()) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("emailCookie")) {
                        email = cookie.getValue();
                        break;
                    }
                }
            }
        }

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Client Registration Status</title>");
        out.println("<style>");
        out.println("body {");
        out.println("    background-image: url('backimage.jpg');");
        out.println("    background-size: cover;");
        out.println("    background-position: center;");
        out.println("    font-family: Arial, sans-serif;");
        out.println("    margin: 0;");
        out.println("}");
        out.println(".container {");
        out.println("    background-color: #fff;");
        out.println("    padding: 40px;");
        out.println("    border-radius: 10px;");
        out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);");
        out.println("    text-align: center;");
        out.println("    margin: 0 auto;");
        out.println("    margin-top: 150px;");
        out.println("    max-width: 400px;");
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
        out.println(".netway-container {");
        out.println("    text-align: center;");
        out.println("    font-size: 50px;");
        out.println("    text-transform: uppercase;");
        out.println("    font-weight: bold;");
        out.println("    animation: mover 6s linear infinite;");
        out.println("    margin-top: 60px;");
        out.println("    display: flex;");
        out.println("    justify-content: center;");
        out.println("    position: relative;");
        out.println("    z-index: 1001;");
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
        out.println(".button-container {");
        out.println("    display: flex;");
        out.println("    flex-direction: column;");
        out.println("    align-items: center;");
        out.println("    margin-top: 20px;");
        out.println("}");
        out.println(".back-button {");
        out.println("    width: 250px;");
        out.println("    height: 40px;");
        out.println("    background-color: #4a7ba6;");
        out.println("    color: #fff;");
        out.println("    border: none;");
        out.println("    border-radius: 5px;");
        out.println("    cursor: pointer;");
        out.println("    font-weight: bold;");
        out.println("    margin-top: 20px;");
        out.println("    text-decoration: none;");
        out.println("    line-height: 40px;");
        out.println("    transition: background-color 0.3s;");
        out.println("    display: block;");
        out.println("}");
        out.println(".back-button:hover {");
        out.println("    background-color: #bc85a3;");
        out.println("}");
        out.println("h2 {");
        out.println("    color: #4a7ba6;");
        out.println("    margin-bottom: 30px;");
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

        if (email != null && !email.isEmpty()) {
            List<String> user_info = new ArrayList<>();
            user_info.add(request.getParameter("firstname"));
            user_info.add(request.getParameter("lastname"));

            Random random = new Random();
            StringBuilder password = new StringBuilder(6);
            for (int i = 0; i < 6; i++) {
                int digit = random.nextInt(10);
                password.append(digit);
            }
            user_info.add(password.toString());
            user_info.add(email);

            List<String> client_info = new ArrayList<>();
            client_info.add(request.getParameter("afm"));

            String phone_number = request.getParameter("phone");

            int status = DatabaseManager.addClient(email, user_info, client_info, phone_number);

            if (status > 0) {
                out.println("<h2>Client registered successfully!</h2>");
            } else {
                out.println("<h2>Failed to register client. Please try again.</h2>");
            }
        } else {
            out.println("<h2>Email is required.</h2>");
        }

        out.println("<div class=\"button-container\">");
        out.println("<a href=\"SellerPage.html\" class=\"back-button\">Go Back</a>");
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }

}
