package ex01;

import java.util.List;

public class BoardService {
    BoardDAO boardDAO;
    public BoardService() {
        boardDAO = new BoardDAO();
    }

    public List<ArticleVO> listArticles() {
        return boardDAO.selectAllArticles();
    }

    public ArticleVO viewArticle(int articleNo) {
        return boardDAO.selectArticle(articleNo);
    }

    public int addArticle(ArticleVO article) {
        return boardDAO.insertNewArticle(article);
    }

    public void modArticle(ArticleVO article) {
        boardDAO.updateArticle(article);
    }
}
