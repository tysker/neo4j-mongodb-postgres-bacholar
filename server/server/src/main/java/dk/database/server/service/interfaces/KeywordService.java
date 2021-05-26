package dk.database.server.service.interfaces;

import dk.database.server.entities.Keyword;
import java.util.Map;

public interface KeywordService {

    Map<Integer,Keyword> getAllKeywords();
    Keyword getKeywordById(int keywordId);
}
