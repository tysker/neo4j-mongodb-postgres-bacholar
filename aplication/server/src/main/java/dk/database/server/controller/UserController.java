package dk.database.server.controller;

import dk.database.server.domain.UserCreation;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.facade.DataFacadeImpl;
import dk.database.server.service.UserServiceImpl;
import dk.database.server.service.interfaces.UserService;
import org.springframework.web.bind.annotation.RequestBody;

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

    private final DataFacadeImpl data = new DataFacadeImpl();

    @Path("/")
    @GET
    public Response getAllUsers(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, User> users = data.getAllUsers();
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
        User user = data.getUserById(userid);
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
        UserKeyword user = data.getUserKeyword(userid);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @Path("/")
    @POST
    public Response addUser(@RequestBody UserCreation userCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        User user = data.addUser(userCreation);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

}
