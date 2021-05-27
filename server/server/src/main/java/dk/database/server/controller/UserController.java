package dk.database.server.controller;

import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.Map;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService service = new UserServiceImpl();

    @Path("/")
    @GET
    public Response getAllUsers(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, User> users = service.getAllUsers();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(users)
                .build();
    }

    @Path("/{userId}")
    @GET
    public Response getUserById(@PathParam("userId") int userid, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        User user = service.getUserById(userid);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @Path("/{userId}/keyword")
    @GET
    public Response getUserKeyword(@PathParam("userId") int userid, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        UserKeyword user = service.getUserKeyword(userid);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

}