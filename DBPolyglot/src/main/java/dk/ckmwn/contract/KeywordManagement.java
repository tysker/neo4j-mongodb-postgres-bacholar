package dk.ckmwn.contract;

import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import java.util.List;

public interface KeywordManagement {

    boolean createKeyword(Keyword keyword);
    boolean deleteKeyword(Keyword keyword);
    Keyword getKeyword(String text);
    boolean addKeywordToStock(Keyword keyword, Stock stock);
    boolean removeKeywordFromStock(Keyword keyword, Stock stock);
    List<Keyword> suggestKeywordsForStock(Stock stock, int width);

}
