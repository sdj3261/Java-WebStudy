package test01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sess")
public class SessionTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        session.setAttribute("name", "이순신");
        out.println("<html><body>");
        out.println("<h1>세션에 이름을 바인딩.</h1>");
        out.println("<a href = 'test01/session1.jsp'> 첫 번째 페이지로 이동 </a>");
        out.println("</body></html>");

    }
}
