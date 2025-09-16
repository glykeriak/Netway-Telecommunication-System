package core.servlets.Client;

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

@WebServlet(name = "UpdateBillServlet", urlPatterns = {"/UpdateBillServlet"})
public class UpdateBillServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String amountPaid = request.getParameter("amountPaid");

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

                sql = "SELECT price FROM Bill WHERE P_ID = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, pId);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    double amountDue = rs.getDouble("price");
                    double amountToPay = Double.parseDouble(amountPaid);
                    if (amountToPay > amountDue) {
                        response.setContentType("text/html");
                        PrintWriter out = response.getWriter();
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Update Bill</title>");
                        out.println("<style>");
                        out.println("body { background-image: url('backimage.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; margin: 0; }");
                        out.println(".container { background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); text-align: center; margin: 0 auto; margin-top: 50px; max-width: 400px; }");
                        out.println(".netway-container { text-align: center; font-size: 50px; text-transform: uppercase; font-weight: bold; animation: mover 6s linear infinite; margin-top: 0; display: flex; justify-content: center; }");
                        out.println(".netway-container span { animation: mover 6s linear infinite; display: inline-block; color: #4a7ba6; font-weight: bolder; }");
                        out.println("@keyframes mover { 0% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } 50% { text-shadow: 0 0 30px rgba(0, 0, 0, 0.30); transform: translateY(30px); } 100% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } }");
                        out.println("h2 { color: #4a7ba6; margin-bottom: 30px; }");
                        out.println("label { display: block; margin-bottom: 10px; color: #4a7ba6; font-weight: bold; }");
                        out.println("</style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class='netway-container'><span>N</span><span>e</span><span>t</span><span>W</span><span>a</span><span>y</span></div>");
                        out.println("<div class='container'>");
                        out.println("<h2>Payment Error</h2>");
                        out.println("<p>The amount entered is greater than the amount due. Please enter a valid amount.</p>");
                        out.println("<form action='PayBillServlet' method='POST'>");
                        out.println("<input type='hidden' name='phoneNumber' value='" + phoneNumber + "'>");
                        out.println("<div class='button-container'><input type='submit' value='Go Back'></div>");
                        out.println("</form>");
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");
                    } else {
                        sql = "UPDATE Bill SET price = price - ? WHERE P_ID = ?";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setDouble(1, amountToPay);
                        pstmt.setString(2, pId);

                        int rowsUpdated = pstmt.executeUpdate();
                        PrintWriter out = response.getWriter();
                        response.setContentType("text/html");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Update Bill</title>");
                        out.println("<style>");
                        out.println("body { background-image: url('backimage.jpg'); background-size: cover; background-position: center; font-family: Arial, sans-serif; margin: 0; }");
                        out.println(".container { background-color: #fff; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.2); text-align: center; margin: 0 auto; margin-top: 50px; max-width: 400px; }");
                        out.println(".netway-container { text-align: center; font-size: 50px; text-transform: uppercase; font-weight: bold; animation: mover 6s linear infinite; margin-top: 0; display: flex; justify-content: center; }");
                        out.println(".netway-container span { animation: mover 6s linear infinite; display: inline-block; color: #4a7ba6; font-weight: bolder; }");
                        out.println("@keyframes mover { 0% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } 50% { text-shadow: 0 0 30px rgba(0, 0, 0, 0.30); transform: translateY(30px); } 100% { text-shadow: 0 0 30px rgba(0, 0, 0, 0); } }");
                        out.println("h2 { color: #4a7ba6; margin-bottom: 30px; }");
                        out.println("label { display: block; margin-bottom: 10px; color: #4a7ba6; font-weight: bold; }");
                        out.println("</style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<div class='netway-container'><span>N</span><span>e</span><span>t</span><span>W</span><span>a</span><span>y</span></div>");
                        out.println("<div class='container'>");
                        out.println("<h2>Update Bill</h2>");
                        if (rowsUpdated > 0) {
                            out.println("<p>Bill payment successful.</p>");
                        } else {
                            out.println("<p>Bill payment failed. Phone number not found.</p>");
                        }
                        out.println("</div>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                } else {
                    response.getWriter().write("Bill not found for the provided P_ID.");
                }
            } else {
                response.getWriter().write("Phone number not found: " + phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Database error: " + e.getMessage());
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
}
