package dk.database.server.service;

import dk.database.server.config.DBConnection;
import dk.database.server.domain.UserKeywordDTO;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.service.interfaces.UserService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private DBConnection db = new DBConnection();


    @Override
    public Map<Integer, User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {

        try(Connection connection = db.connect())
        {
            String sql = "SELECT * FROM users WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int id = rs.getInt("id");
                    String userName = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("pwd");
                    User user = new User(id, userName, email, password);
                    return user;
                }
            }
            return null;
        }
    }

    @Override
    public UserKeyword getUserKeyword() throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {

            String sql = "select * from view_users_keywords;";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();
                Map<Integer, Keyword> keywords = new HashMap<>();
                if(rs.next()) {
                    int uId = rs.getInt("user_id");
                    int keywordId = rs.getInt("keyword_id");
                    String keyword = rs.getString("keyword");
                    String userName = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("pwd");
                    keywords.put(keywordId, new Keyword(keywordId, keyword));
                    UserKeyword user = new UserKeyword(uId, userName, email, password, keywords);
                    return user;
                }
            }
            return null;
        }
    }

}
