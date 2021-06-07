package dk.database.server.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserStockDTO {

    private int userId, stockId;
    private String stock, userName, email;

}
