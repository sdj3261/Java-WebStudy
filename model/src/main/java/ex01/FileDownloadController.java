package ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/download.do")
public class FileDownloadController extends HttpServlet {
    private static final String ARTICLE_IMAGE_REPOSITORY = "C:\\board\\article_image";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandle(req,resp);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String imageFileName = (String)request.getParameter("imageFileName");
        String articleNo = request.getParameter("articleNO");
        System.out.println("imageFileName = " + imageFileName + " , ArticleNo : " + articleNo);
        OutputStream out = response.getOutputStream();
        String path = ARTICLE_IMAGE_REPOSITORY + "\\" + articleNo + "\\" + imageFileName;
        File imageFile = new File(path);

        response.setHeader("Cache-control", "no-cache");
        response.addHeader("Content-disposition", "attachment;fileName=" + imageFileName);
        FileInputStream in = new FileInputStream(imageFile);
        //파일전송
        byte[] buffer = new byte[1024 * 8]; //8kb씩 전송 .. 버퍼를 이용
        while(true) {
            int count = in.read(buffer);
            if(count == -1) {
                break;
            }
            out.write(buffer,0,count);
        }
        in.close();
        out.close();
    }
    @Override
    public void init() throws ServletException {
        super.init();
    }
}
