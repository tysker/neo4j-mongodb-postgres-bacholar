package dk.database.server.service;

import dk.database.server.config.DBConnection;
import dk.database.server.domain.KeywordCreation;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.entities.UserStockKeyword;
import dk.database.server.service.interfaces.KeywordService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeywordServiceImpl implements KeywordService {

    private final DBConnection db = new DBConnection();

    /**
     *
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Map<Integer, Keyword> getAllKeywords() throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {
            Map<Integer, Keyword> keywords = new HashMap<>();

            String sql = "SELECT * FROM keywords;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();

                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String keyword = rs.getString("keyword");
                    keywords.put(id, new Keyword(id, keyword));

                }
                return keywords;
            }
        }
    }

    /**
     *
     * @param keywordId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Keyword getKeywordById(int keywordId) throws SQLException, ClassNotFoundException  {

        try(Connection connection = db.connect())
        {
            Keyword keyword = new Keyword();
            String sql = "SELECT * FROM keywords WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, keywordId);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int id = rs.getInt("id");
                    String key = rs.getString("keyword");
                    keyword = new Keyword(id, key);;
                }
                return keyword;
            }
        }
    }

    /**
     *
     * @param keywordCreation
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Keyword addKeyword(KeywordCreation keywordCreation) throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {
            String sql = "{ ? = call add_keyword(?)}";

            Keyword keyword;

            try (CallableStatement stmt= connection.prepareCall(sql))
            {
                stmt.setString(2, keywordCreation.getKeyword());
                stmt.registerOutParameter(1,Types.INTEGER);
                stmt.execute();

                int newId = stmt.getInt(1);
                keyword = new Keyword(newId, keywordCreation.getKeyword());
                return keyword;
            }
        }
    }

    @Override
    public UserStockKeyword getKeywordByUserIdAndStockName(int userId, String stockName) throws SQLException, ClassNotFoundException {
        User user = null;
        Stock stock = null;

        try(Connection connection = db.connect()) {

            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("pwd");
                    user = new User(id, username, email, password);
                }
            }
        }

        try(Connection connection = db.connect()) {
            String sql = "SELECT * FROM stocks WHERE stockname = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, stockName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String stockname = rs.getString("stockname");
                    stock = new Stock(id, stockname);
                }
            }
        }

        try(Connection connection = db.connect()) {
            String sql = "SELECT * FROM view_users_keywords_stocks WHERE user_id = ? AND stockname = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                ps.setString(2, stockName);
                ResultSet rs = ps.executeQuery();
                List<Keyword> keywordList = new ArrayList<>();
                while(rs.next())
                {
                    int id = rs.getInt("keyword_id");
                    String key = rs.getString("keyword");
                    keywordList.add(new Keyword(id, key));
                }
                return new UserStockKeyword(user, stock, keywordList);
            }
        }
    }
}
