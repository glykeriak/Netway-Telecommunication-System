package core.servlets.Client;

import core.databases.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PayBillServlet", urlPatterns = {"/PayBillServlet"})
public class PayBillServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseManager.getConnection();

            String sql = "SELECT P_ID FROM PhoneNumber WHERE number = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, phoneNumber);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                String pId = rs.getString("P_ID");

                pstmt.close();
                rs.close();

                sql = "SELECT * FROM Bill WHERE P_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, pId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    double amountDue = rs.getDouble("price");
                    PrintWriter out = response.getWriter();
                    response.setContentType("text/html");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Pay Bill</title>");
                    out.println("<style>");
                    out.println("body { background-image: url('backimage.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; margin: 0; }");
                    out.println(".header { display: flex; justify-content: space-between; align-items: center; padding: 10px 20px; background-color: rgba(255, 255, 255, 0.8); box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); position: fixed; width: 100%; top: 0; left: 0; z-index: 1000; }");
                    out.println(".header-left { display: flex; flex-direction: column; }");
                    out.println(".header-buttons { display: flex; gap: 10px; }");
                    out.println(".logout-button, .session-button { background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; padding: 10px 20px; text-decoration: none; transition: background-color 0.3s; }");
                    out.println(".logout-button:hover, .session-button:hover { background-color: #bc85a3; }");
                    out.println(".user-name { font-size: 18px; font-weight: bold; color: #4a7ba6; margin-bottom: 10px; }");
                    out.println(".container { background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); text-align: center; margin: 0 auto; margin-top: 150px; max-width: 400px; }");
                    out.println(".netway-container { text-align: center; font-size: 50px; text-transform: uppercase; font-weight: bold; animation: mover 6s linear infinite; margin-top: 60px; display: flex; justify-content: center; position: relative; z-index: 1001; }");
                    out.println(".netway-container span { animation: mover 6s linear infinite; display: inline-block; color: #4a7ba6; font-weight: bolder; }");
                    out.println("@keyframes mover { 0% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } 50% { text-shadow: 0 0 30px rgba(0, 0, 0, 0.30); transform: translateY(30px); } 100% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } }");
                    out.println("span:nth-child(1) { animation-delay: 0.5s; color: #457fb1; }");
                    out.println("span:nth-child(2) { animation-delay: 1s; color: #79b4d2; }");
                    out.println("span:nth-child(3) { animation-delay: 1.5s; color: #457fb1; }");
                    out.println("span:nth-child(4) { animation-delay: 2s; color: #79b4d2; }");
                    out.println("span:nth-child(5) { animation-delay: 2.5s; color: #457fb1; }");
                    out.println("span:nth-child(6) { animation-delay: 3s; color: #79b4d2; }");
                    out.println("h2 { color: #4a7ba6; margin-bottom: 30px; }");
                    out.println("label { display: block; margin-bottom: 10px; color: #4a7ba6; font-weight: bold; }");
                    out.println("input[type='text'] { width: 250px; height: 30px; border-radius: 5px; border: 1px solid #4a7ba6; padding: 5px; margin-bottom: 15px; }");
                    out.println("input[type='submit'] { width: 250px; height: 40px; background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; margin: 5px; }");
                    out.println("input[type='submit']:hover { background-color: #bc85a3; }");
                    out.println(".button-container { display: flex; flex-direction: column; align-items: center; margin-top: 20px; }");
                    out.println(".back-button { width: 250px; height: 40px; background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; margin-top: 20px; text-decoration: none; line-height: 40px; transition: background-color 0.3s; display: block; text-align: center; }");
                    out.println(".back-button:hover { background-color: #bc85a3; }");
                    out.println("</style>");
                    out.println("<script>");
                    out.println("window.onload = function () { const userName = getCookie('emailCookie'); if (userName) { document.getElementById('user-name').innerText = 'Welcome, ' + userName; } };");
                    out.println("function getCookie(name) { let cookieArr = document.cookie.split(';'); for (let i = 0; i < cookieArr.length; i++) { let cookiePair = cookieArr[i].split('='); if (name === cookiePair[0].trim()) { return decodeURIComponent(cookiePair[1]); } } return null; }");
                    out.println("</script>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='header'>");
                    out.println("<div class='header-left'>");
                    out.println("<div id='user-name' class='user-name'></div>");
                    out.println("<form action='show-session' method='post' style='margin: 0;'>");
                    out.println("<input type='submit' value='Show Session Info' class='session-button'>");
                    out.println("</form>");
                    out.println("</div>");
                    out.println("<a href='./logout' class='logout-button'>Log Out</a>");
                    out.println("</div>");
                    out.println("<div class='netway-container'><span>N</span><span>e</span><span>t</span><span>W</span><span>a</span><span>y</span></div>");
                    out.println("<div class='container'>");
                    out.println("<h2>Pay Bill</h2>");
                    out.println("<p>Amount Due: " + amountDue + "</p>");
                    out.println("<form action='UpdateBillServlet' method='POST'>");
                    out.println("<input type='hidden' name='phoneNumber' value='" + phoneNumber + "'>");
                    out.println("<label for='amountPaid'>Amount to Pay:</label>");
                    out.println("<input type='text' id='amountPaid' name='amountPaid' oninput='validateAmount(" + amountDue + ")'><br>");
                    out.println("<div class='button-container'><input type='submit' value='Pay Bill' id='payButton'></div>");
                    out.println("</form>");
                    out.println("<a href='ClientPage.html' class='back-button'>Go Back</a>");
                    out.println("</div>");
                    out.println("<script>");
                    out.println("function validateAmount(amountDue) {");
                    out.println("var amountPaid = document.getElementById('amountPaid').value;");
                    out.println("var payButton = document.getElementById('payButton');");
                    out.println("if (parseFloat(amountPaid) > amountDue) {");
                    out.println("payButton.disabled = true;");
                    out.println("alert('The amount entered is greater than the amount due.');");
                    out.println("} else {");
                    out.println("payButton.disabled = false;");
                    out.println("}");
                    out.println("}");
                    out.println("</script>");
                    out.println("</body>");
                    out.println("</html>");
                } else {
                    response.getWriter().write(generateErrorPage("No bill found for Phone Number: " + phoneNumber));
                }
            } else {
                response.getWriter().write(generateErrorPage("Phone number not found: " + phoneNumber));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write(generateErrorPage("Database error: " + e.getMessage()));
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateErrorPage(String errorMessage) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Error</title>");
        sb.append("<style>");
        sb.append("body { background-image: url('backimage.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; margin: 0; }");
        sb.append(".header { display: flex; justify-content: space-between; align-items: center; padding: 10px 20px; background-color: rgba(255, 255, 255, 0.8); box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); position: fixed; width: 100%; top: 0; left: 0; z-index: 1000; }");
        sb.append(".header-left { display: flex; flex-direction: column; }");
        sb.append(".header-buttons { display: flex; gap: 10px; }");
        sb.append(".logout-button, .session-button { background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; padding: 10px 20px; text-decoration: none; transition: background-color 0.3s; }");
        sb.append(".logout-button:hover, .session-button:hover { background-color: #bc85a3; }");
        sb.append(".user-name { font-size: 18px; font-weight: bold; color: #4a7ba6; margin-bottom: 10px; }");
        sb.append(".container { background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); text-align: center; margin: 0 auto; margin-top: 150px; max-width: 400px; }");
        sb.append(".netway-container { text-align: center; font-size: 50px; text-transform: uppercase; font-weight: bold; animation: mover 6s linear infinite; margin-top: 60px; display: flex; justify-content: center; position: relative; z-index: 1001; }");
        sb.append(".netway-container span { animation: mover 6s linear infinite; display: inline-block; color: #4a7ba6; font-weight: bolder; }");
        sb.append("@keyframes mover { 0% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } 50% { text-shadow: 0 0 30px rgba(0, 0, 0, 0.30); transform: translateY(30px); } 100% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } }");
        sb.append("span:nth-child(1) { animation-delay: 0.5s; color: #457fb1; }");
        sb.append("span:nth-child(2) { animation-delay: 1s; color: #79b4d2; }");
        sb.append("span:nth-child(3) { animation-delay: 1.5s; color: #457fb1; }");
        sb.append("span:nth-child(4) { animation-delay: 2s; color: #79b4d2; }");
        sb.append("span:nth-child(5) { animation-delay: 2.5s; color: #457fb1; }");
        sb.append("span:nth-child(6) { animation-delay: 3s; color: #79b4d2; }");
        sb.append("h2 { color: #4a7ba6; margin-bottom: 30px; }");
        sb.append("label { display: block; margin-bottom: 10px; color: #4a7ba6; font-weight: bold; }");
        sb.append("input[type='text'] { width: 250px; height: 30px; border-radius: 5px; border: 1px solid #4a7ba6; padding: 5px; margin-bottom: 15px; }");
        sb.append("input[type='submit'] { width: 250px; height: 40px; background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; margin: 5px; }");
        sb.append("input[type='submit']:hover { background-color: #bc85a3; }");
        sb.append(".button-container { display: flex; flex-direction: column; align-items: center; margin-top: 20px; }");
        sb.append(".back-button { width: 250px; height: 40px; background-color: #4a7ba6; color: #fff; border: none; border-radius: 5px; cursor: pointer; font-weight: bold; margin-top: 20px; text-decoration: none; line-height: 40px; transition: background-color 0.3s; display: block; text-align: center; }");
        sb.append(".back-button:hover { background-color: #bc85a3; }");
        sb.append("</style>");
        sb.append("<script>");
        sb.append("window.onload = function () { const userName = getCookie('emailCookie'); if (userName) { document.getElementById('user-name').innerText = 'Welcome, ' + userName; } };");
        sb.append("function getCookie(name) { let cookieArr = document.cookie.split(';'); for (let i = 0; i < cookieArr.length; i++) { let cookiePair = cookieArr[i].split('='); if (name === cookiePair[0].trim()) { return decodeURIComponent(cookiePair[1]); } } return null; }");
        sb.append("</script>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<div class='header'>");
        sb.append("<div class='header-left'>");
        sb.append("<div id='user-name' class='user-name'></div>");
        sb.append("<form action='show-session' method='post' style='margin: 0;'>");
        sb.append("<input type='submit' value='Show Session Info' class='session-button'>");
        sb.append("</form>");
        sb.append("</div>");
        sb.append("<a href='./logout' class='logout-button'>Log Out</a>");
        sb.append("</div>");
        sb.append("<div class='netway-container'><span>N</span><span>e</span><span>t</span><span>W</span><span>a</span><span>y</span></div>");
        sb.append("<div class='container'>");
        sb.append("<h2>Error</h2>");
        sb.append("<p>" + errorMessage + "</p>");
        sb.append("<a href='ClientPage.html' class='back-button'>Go Back</a>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
