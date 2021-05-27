package dk.database.server.controller;

import dk.database.server.entities.Keyword;
import dk.database.server.entities.User;
import dk.database.server.service.KeywordServiceImpl;
import dk.database.server.service.interfaces.KeywordService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.Map;

@Path("/keywords")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class KeywordController {

    private final KeywordService service = new KeywordServiceImpl();

    @Path("/")
    @GET
    public Response getAllKeywords(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, Keyword> keywords = service.getAllKeywords();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(keywords)
                .build();
    }

    @Path("/{keywordId}")
    @GET
    public Response getUserById(@PathParam("keywordId") int keywordId, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Keyword keyword = service.getKeywordById(keywordId);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(keyword)
                .build();
    }
}
