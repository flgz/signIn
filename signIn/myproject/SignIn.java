package myproject;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "signIn", urlPatterns = "/signIn")
public class SignIn extends HttpServlet {
    private String realEmail;
    private String realPassword;

    @Override
    public void init() throws ServletException {
        realEmail = getServletConfig().getInitParameter("email");
        realPassword = getServletConfig().getInitParameter("password");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String emailPosted = req.getParameter("email");
        String passPosted = req.getParameter("password");
        if (emailPosted.equalsIgnoreCase(realEmail) && passPosted.equals(realPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("name", "client");
            session.setMaxInactiveInterval(30);
            resp.sendRedirect("hello");
        } else {
            resp.sendRedirect("index.html");
        }
    }
}
