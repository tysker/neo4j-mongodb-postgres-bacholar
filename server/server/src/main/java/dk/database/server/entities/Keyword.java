package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class Keyword implements Serializable {

    private int id;
    private String keyword;
}
