import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest r, HttpServletResponse w) throws ServletException, IOException {
        r.setCharacterEncoding("UTF-8");
        w.setContentType("text/html;charset=utf-8");
        PrintWriter out = w.getWriter();
        String id = r.getParameter("user_id");
        String pw = r.getParameter("user_pw");

        String data = "<html>";
        data += "<body>";
        data += "아이디 : " + id;
        data += "<br>";
        data += "비밀번호 : " + pw;
        data += "</body>";
        data += "</html>";
        out.print(data);
    }

    @Override
    public void destroy() {
        System.out.println("destroy 호출");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init 호출");
    }
}
