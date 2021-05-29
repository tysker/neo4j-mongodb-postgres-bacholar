package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserStock {

    private int id;
    private String userName, email, password;

    private Map<Integer, Stock> stockList = new HashMap<>();
}
