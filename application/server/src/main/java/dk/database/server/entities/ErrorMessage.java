package dk.database.server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

    private String errorMessage;
    private int errorCode;
    private String documentation;
}
