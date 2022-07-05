package ex01;

import ex01.ArticleVO;

import java.util.List;

public class BoardService {
    BoardDAO boardDAO;
    public BoardService() {
        boardDAO = new BoardDAO();
    }

    public List<ArticleVO> listArticles() {
        List<ArticleVO> articlesList = boardDAO.selectAllArticles();
        return articlesList;
    }
}
