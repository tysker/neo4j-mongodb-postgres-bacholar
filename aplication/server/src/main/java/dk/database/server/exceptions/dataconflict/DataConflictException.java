package dk.database.server.exceptions.dataconflict;

/**
 * DataConflictException
 */
public class DataConflictException extends RuntimeException {

    public DataConflictException(String message) {
        super(message);
    }
}
