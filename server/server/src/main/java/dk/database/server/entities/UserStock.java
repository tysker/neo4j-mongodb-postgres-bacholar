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
public class UserStock {

    private int id;
    private String userName, email, password;

    private Map<Integer, Stock> stockList = new HashMap<>();
}
