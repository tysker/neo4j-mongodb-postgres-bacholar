package dk.database.server.controller;

import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/server")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class postgres {

    private UserService service = new UserServiceImpl();

    @Path("user")
    @GET
    public Response getUserById() throws SQLException, ClassNotFoundException {
        User user = service.getUserById(1);
        return Response.status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @Path("user/keyword")
    @GET
    public Response getUserKeyword() throws SQLException, ClassNotFoundException {
        UserKeyword user = service.getUserKeyword();
        return Response.status(Response.Status.OK)
                .entity(user)
                .build();
    }
}
