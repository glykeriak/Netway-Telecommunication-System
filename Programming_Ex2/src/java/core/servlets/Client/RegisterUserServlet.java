/*package core.servlets.Client;

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

@WebServlet(name = "RegisterUserServlet", urlPatterns = {"/registeruser"})
public class RegisterUserServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie[] cookies = request.getCookies();
        String emailCookie = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("emailCookie")) {
                    emailCookie = cookie.getValue();
                }
            }
        }

        if (emailCookie != null) {
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
            user_info.add(emailCookie);

            List<String> client_info = new ArrayList<>();
            client_info.add(request.getParameter("afm"));

            //int status = DatabaseManager.addClient(emailCookie, user_info, client_info);

            if (status > 0) {
                response.sendRedirect("./SellerPage.html");
            } else {
                out.println("<html><body><h2>Linking failed. Please try again.</h2></body></html>");
                request.getRequestDispatcher("./index.html").include(request, response);
            }
        } else {
            out.println("<html><body><h2>Required data not found in cookies.</h2></body></html>");
            request.getRequestDispatcher("./index.html").include(request, response);
        }
        out.close();
    }
}*/
