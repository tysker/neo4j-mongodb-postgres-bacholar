package dk.ckmwn.contract;

import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;

import java.util.Collection;

public interface KeywordManagement {

    boolean createKeyword(Keyword keyword);
    boolean deleteKeyword(Keyword keyword);
    Keyword getKeyword(String text);
    boolean addKeywordToStock(Keyword keyword, Stock stock);
    boolean removeKeywordFromStock(Keyword keyword, Stock stock);
    Collection<Keyword> suggestKeywordsForStock(Stock stock, int width);

}
