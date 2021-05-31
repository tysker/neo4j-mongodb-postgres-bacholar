package dk.database.server.exceptions.dataconflict;

import dk.database.server.entities.ErrorMessage;
import dk.database.server.exceptions.dataconflict.DataConflictException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataConflictExceptionMapper implements ExceptionMapper<DataConflictException> {
    @Override
    public Response toResponse(DataConflictException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 409, "http://localhost:8080/api/");
        return Response
                .status(Response.Status.CONFLICT)
                .entity(errorMessage)
                .build();
    }
}
