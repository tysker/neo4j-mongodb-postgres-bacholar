package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String userName, email, password;

//    private Map<Integer, Stock> stockList = new HashMap<>();
//    private Map<Integer, Keyword> keywordList = new HashMap<>();

//    public User(int id, String userName, String email, String password) {
//        this.id = id;
//        this.userName = userName;
//        this.email = email;
//        this.password = password;
//    }
}
