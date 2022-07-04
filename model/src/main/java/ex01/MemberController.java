package ex01;


import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
    MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        try {
            memberDAO = new MemberDAO();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    private void doHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String nextPage = null;
        String action = req.getPathInfo();
        System.out.println("action : " + action);

        if (action == null || action.equals("/listMembers.do")) {
            List<MemberVO> membersList = memberDAO.listMembers();
            req.setAttribute("membersList", membersList);
            nextPage = "/ex01/listMembers.jsp";
        }

        else if (action.equals("/addMember.do")) {
            String id = req.getParameter("id");
            String pwd = req.getParameter("pwd");
            String name = req.getParameter("name");
            String email = req.getParameter("email");

            MemberVO Insert_VO = new MemberVO(id,pwd,name,email);
            memberDAO.addMember(Insert_VO);
            nextPage = ("/member/listMembers.do");
        } else if (action.equals("/membersForm.do")) {
            nextPage = "/ex01/memberForm.jsp";
        }
        else {
            List<MemberVO> membersList = memberDAO.listMembers();
            req.setAttribute("membersList", membersList);
            nextPage = "/ex01/listMembers.jsp";
        }
        RequestDispatcher dispatch = req.getRequestDispatcher(nextPage);
        dispatch.forward(req, resp);
    }
}
