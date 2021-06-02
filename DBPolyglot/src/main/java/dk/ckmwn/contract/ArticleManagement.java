package dk.ckmwn.contract;

import dk.ckmwn.dto.Article;
import dk.ckmwn.dto.Stock;

public interface ArticleManagement {

    boolean createArticle(Article article);
    boolean deleteArticle(String id);
    Article getArticle(String id);
    boolean updateArticle(Article article);

}
