package ex01;

import ex01.ArticleVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
    private DataSource dataFactory;
    Connection conn;
    PreparedStatement pstmt;

    public BoardDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ArticleVO> selectAllArticles() {
        List<ArticleVO> articlesList = new ArrayList();
        try {
            conn = dataFactory.getConnection();
            String query = "SELECT LEVEL,articleNO,parentNO,title,content,id,writeDate"
                    + " from t_board"
                    + " START WITH  parentNO=0" + " CONNECT BY PRIOR articleNO=parentNO"
                    + " ORDER SIBLINGS BY articleNO DESC";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int level = rs.getInt("level");
                int articleNO = rs.getInt("articleNO");
                int parentNO = rs.getInt("parentNO");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String id = rs.getString("id");
                Date writeDate = rs.getDate("writeDate");
                ArticleVO article = new ArticleVO();
                article.setLevel(level);
                article.setArticleNO(articleNO);
                article.setParentNO(parentNO);
                article.setTitle(title);
                article.setContent(content);
                article.setId(id);
                article.setWriteDate(writeDate);
                articlesList.add(article);
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articlesList;
    }

    private int getNewArticleNO() {
       try {
           conn = dataFactory.getConnection();
           String query = "SELECT max(articleNo) from t_board";
           System.out.println(query);
           pstmt = conn.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
           if (rs.next()) {
               return rs.getInt("articleNo") + 1;
           }
           rs.close();
           pstmt.close();
           conn.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
    }
    public void insertNewArticle(ArticleVO article) {
        try {
            conn = dataFactory.getConnection();
            int articleNo = getNewArticleNO();
            int parentNo = article.getParentNO();
            String title = article.getTitle();
            String content = article.getContent();
            String id = article.getId();
            String imageFileName = article.getImageFileName();
            String query = "INSERT INTO t_board (articleNo, parentNo, title, content, imageFileName, id)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,articleNo);
            pstmt.setInt(2,parentNo);
            pstmt.setString(3,title);
            pstmt.setString(4,content);
            pstmt.setString(5,imageFileName);
            pstmt.setString(6, id);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
