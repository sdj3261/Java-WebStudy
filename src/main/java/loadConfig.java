import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "loadConfig", urlPatterns = {"/loadConfig"}, loadOnStartup = 1)
public class loadConfig extends HttpServlet {
    private ServletContext context;
    @Override
    public void init() throws ServletException {
        context = getServletContext();
        String menu_member = context.getInitParameter("menu_member");
        String menu_order = context.getInitParameter("menu_order");
        String menu_goods = context.getInitParameter("menu_goods");

        context.setAttribute("menu_member", menu_member);
        context.setAttribute("menu_order", menu_order);
        context.setAttribute("menu_goods", menu_goods);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        String menu_member = (String) context.getAttribute("menu_member");
        String menu_order = (String) context.getAttribute("menu_order");
        String menu_goods = (String) context.getAttribute("menu_goods");

        out.print("<html><body>");
        out.print("<table border = 1 cellspacing=0> <tr>메뉴 이름</tr>");
        out.print("<tr><td>" + menu_member + "<</tr></td>");
        out.print("<tr><td>" + menu_order + "<</tr></td>");
        out.print("<tr><td>" + menu_goods + "<</tr></td>");
        out.print("</table></body><html>");

    }
}
