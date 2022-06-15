package ex03;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/firsto")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
       // redirect 방식 resp.sendRedirect("secondo")
        // 헤더 추가 방식 resp.addHeader("Refresh", "1;url=secondo");
        // js 방식
        /*out.print("<script type='text/javascript'>");
        out.print("location.href='secondo';");
        out.print("</script>");*/
        RequestDispatcher dispatch = req.getRequestDispatcher("secondo");
        dispatch.forward(req,resp);

        //바인딩을 통한 jsp 간 대량의 데이터 전달
    }
}
