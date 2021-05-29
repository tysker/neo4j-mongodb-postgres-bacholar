package dk.database.server.service.interfaces;

import dk.database.server.entities.Keyword;

import java.sql.SQLException;
import java.util.Map;

public interface KeywordService {

    Map<Integer,Keyword> getAllKeywords() throws SQLException, ClassNotFoundException;
    Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException;
    Keyword addKeyword(Keyword keyword) throws SQLException, ClassNotFoundException;
}
