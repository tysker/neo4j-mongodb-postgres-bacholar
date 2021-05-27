package dk.database.server.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserKeywordDTO {

    private int userId;
    private String userName, email;
    private List<String> keywords = new ArrayList<>();


}
