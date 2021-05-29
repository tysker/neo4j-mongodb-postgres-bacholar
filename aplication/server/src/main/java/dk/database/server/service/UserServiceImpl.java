package dk.database.server.service;

import dk.database.server.config.DBConnection;
import dk.database.server.domain.UserCreation;
import dk.database.server.domain.UserKeywordCreation;
import dk.database.server.domain.UserStockCreation;
import dk.database.server.entities.*;
import dk.database.server.service.interfaces.UserService;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private DBConnection db = new DBConnection();


    @Override
    public Map<Integer, User> getAllUsers() throws SQLException, ClassNotFoundException {

        try (Connection connection = db.connect()) {
            Map<Integer, User> users = new HashMap<>();

            String sql = "SELECT * FROM view_users;";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String userName = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("pwd");
                    User user = new User(id, userName, email, password);
                    users.put(id, user);
                }
                return users;
            }
        }
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {

        try (Connection connection = db.connect()) {
            String sql = "SELECT * FROM view_users WHERE id = ?";
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
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
    public UserKeyword getUserKeyword(int userId) throws SQLException, ClassNotFoundException {
        try (Connection connection = db.connect()) {
            UserKeyword user = new UserKeyword();
            Map<Integer, Keyword> keywords = new HashMap<>();

            String sql = "SELECT * FROM view_users_keywords WHERE user_id = ?;";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int uId = rs.getInt("user_id");
                    int keywordId = rs.getInt("keyword_id");
                    String keyword = rs.getString("keyword");
                    String userName = rs.getString("username");
                    String email = rs.getString("email");
                    String password = rs.getString("pwd");
                    keywords.put(keywordId, new Keyword(keywordId, keyword));
                    user = new UserKeyword(uId, userName, email, password, keywords);
                }
                return user;
            }
        }
    }

    @Override
    public User addUser(UserCreation userCreation) throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {
            String sql = "{? = call add_user(?, ?, ?)}";

            User user;

            try (CallableStatement stmt= connection.prepareCall(sql))
            {
                stmt.setString(2, userCreation.getUserName());
                stmt.setString(3, userCreation.getPassword());
                stmt.setString(4, userCreation.getEmail());
                stmt.registerOutParameter(1,Types.INTEGER);
                stmt.execute();

                int newId = stmt.getInt(1);
                user = new User(newId, userCreation.getUserName(), userCreation.getEmail(), userCreation.getPassword());
                return user;
            }
        }
    }

    @Override
    public void addUserStock(UserStockCreation userStockCreation) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void addUserKeyword(UserKeywordCreation userKeywordCreation) throws SQLException, ClassNotFoundException {

    }
}
