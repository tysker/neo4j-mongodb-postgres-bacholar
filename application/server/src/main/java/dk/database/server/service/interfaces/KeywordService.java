package dk.database.server.service.interfaces;

import dk.database.server.domain.KeywordCreation;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.UserStockKeyword;

import java.sql.SQLException;
import java.util.Map;

public interface KeywordService {

    Map<Integer,Keyword> getAllKeywords() throws SQLException, ClassNotFoundException;
    Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException;
    Keyword addKeyword(KeywordCreation keywordCreation) throws SQLException, ClassNotFoundException;
    UserStockKeyword getKeywordByUserIdAndStockName(int userId, String stockName) throws SQLException, ClassNotFoundException;
}
