import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("do get 호출");
    }


    @Override
    public void destroy() {
        System.out.println("destory 호출");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init 호출");
    }
}
