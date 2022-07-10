package ex01;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                    + " START WITH  parentNO = 0" + " CONNECT BY PRIOR articleNO = parentNO"
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

    public ArticleVO selectArticle(int articleNo) {
        ArticleVO article = new ArticleVO();
        try {
            conn = dataFactory.getConnection();
            String query = "SELECT articleNO,parentNO,title,content,imageFileName,id,writeDate"
                    + " FROM t_board"
                    + " WHERE articleNo = ?";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,articleNo);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int _articleNO = rs.getInt("articleNO");
            int parentNO = rs.getInt("parentNO");
            String title = rs.getString("title");
            String content = rs.getString("content");
            String imageFileName = rs.getString("imageFileName");
            String id = rs.getString("id");
            Date writeDate = rs.getDate("writeDate");
            article.setArticleNO(_articleNO);
            article.setParentNO(parentNO);
            article.setTitle(title);
            article.setContent(content);
            article.setId(id);
            article.setWriteDate(writeDate);
            article.setImageFileName(imageFileName);

            rs.close();
            pstmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    private int getNewArticleNO() {
       try {
           conn = dataFactory.getConnection();
           String query = "SELECT max(articleNo) from t_board";
           System.out.println(query);
           pstmt = conn.prepareStatement(query);
           ResultSet rs = pstmt.executeQuery();
           if (rs.next()) {
               return rs.getInt(1) + 1;
           }
           rs.close();
           pstmt.close();
           conn.close();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return 0;
    }
    public int insertNewArticle(ArticleVO article) {
        int articleNo = getNewArticleNO();
        try {
            conn = dataFactory.getConnection();
            int parentNo = article.getParentNO();
            String title = article.getTitle();
            String content = article.getContent();
            String id = article.getId();
            String imageFileName = article.getImageFileName();
            String query = "INSERT INTO t_board (articleNo, parentNo, title, content, imageFileName, id)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

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
        return articleNo;
    }

    public void updateArticle(ArticleVO article) {
        int articleNo = article.getArticleNO();
        String title = article.getTitle();
        String content = article.getContent();
        String imageFileName = article.getImageFileName();

        try {
            conn = dataFactory.getConnection();
            String query = "update t_board set title = ?, content = ?";
            if(imageFileName != null && imageFileName.length() != 0) {
                query += ", imageFileName = ?";
            }
                query += " where articleNO = ?";

            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,title);
            pstmt.setString(2,content);
            if(imageFileName != null && imageFileName.length() != 0) {
                pstmt.setString(3,imageFileName);
                pstmt.setInt(4,articleNo);
            } else {
                pstmt.setInt(3, articleNo);
            }

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteArticle(int articleNo) {
        try {
            conn = dataFactory.getConnection();
            String query = "delete from t_board";
            query += " WHERE articleNO in (";
            query += "SELECT articleNO FROM t_board ";
            query += "START WITH articleNO = ?";
            query += " CONNECT BY PRIOR articleNO = parentNO";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,articleNo);
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> selectRemovedArticles(int articleNo) {
        List<Integer> articleNoList = new ArrayList<Integer>();
        try {
            conn = dataFactory.getConnection();
            String query = "select articleNO from t_board ";
            query += "SELECT articleNO FROM t_board ";
            query += "START WITH articleNO = ?";
            query += " CONNECT BY PRIOR articleNO = parentNO";
            System.out.println(query);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,articleNo);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                articleNo = rs.getInt("articleNo");
                articleNoList.add(articleNo);
            }
            rs.close();
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleNoList;
    }
}
