package dk.database.server.entities;

import lombok.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserKeyword implements Serializable {

    private int id;
    private String userName, email, password;
    private Map<Integer, Keyword> keywordList;

}
