package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Keyword {

    private int id;
    private String keyword;
}
