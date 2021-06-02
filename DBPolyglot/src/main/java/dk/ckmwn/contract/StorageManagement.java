package dk.ckmwn.contract;

import dk.ckmwn.dto.Article;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;

import java.util.Collection;

public interface StorageManagement {

    boolean createStock(Stock stock);
    boolean addKeywordToStock(Stock stock, Keyword keyword);
    boolean removeKeywordFromStock(Stock stock, Keyword keyword);
    Collection<Keyword> suggestKeywordsForStock(Stock stock, int width);
    boolean createArticle(Article article);
    Article getArticle(String id);
}
