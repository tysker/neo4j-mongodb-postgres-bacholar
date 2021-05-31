package dk.database.server.exceptions.dataconflict;

public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
