import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/input")
public class InputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String user_id = req.getParameter("user_id");
        String user_pw = req.getParameter("user_pw");
        String[] subject = req.getParameterValues("subject");

        for(String str : subject) {
            System.out.println("체크된 항목은 " + str);
        }

        Enumeration enu = req.getParameterNames();

        while(enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String[] values = req.getParameterValues(name);
            for(String value : values) {
                System.out.println("name " + name + "value=" + value);
            }
        }
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
