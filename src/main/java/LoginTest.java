import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logintest")
public class LoginTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_pw");
        PrintWriter wr = resp.getWriter();

        if (id.length() != 0) {
            String data = "<html>";
            data += "<body>";
            data += id + "님!! 로그인 하였습니다.";
            data += "</body>";
            data += "</html>";

            wr.print(data);
        }
        else {
            String data = "<html>";
            data += "<body>";
            data += "<h2>아이디를 입력하세요.</h2>";
            data += "<br>";
            data += "<a href='http://localhost:8080/webShop_war_exploded/test01/login.html'> 로그인 창으로 이동 </a>";
            data += "</body>";
            data += "</html>";
            wr.print(data);
        }

        wr.close();
    }

    @Override
    public void destroy() {
        System.out.println("사라짐");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("초기화");
    }
}
