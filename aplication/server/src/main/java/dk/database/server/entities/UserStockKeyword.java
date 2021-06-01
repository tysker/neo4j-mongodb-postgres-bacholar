package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserStockKeyword {

    private User user;
    private Stock stock;
    List<Keyword> keywordList;
}
