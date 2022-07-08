package ex01;

import ex01.ArticleVO;

import java.util.List;

public class BoardService {
    BoardDAO boardDAO;
    public BoardService() {
        boardDAO = new BoardDAO();
    }

    public List<ArticleVO> listArticles() {
        return boardDAO.selectAllArticles();
    }

    public int addArticle(ArticleVO article) {
        return boardDAO.insertNewArticle(article);
    }
}
