package dk.database.server.controller;

import dk.database.server.domain.KeywordCreation;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.UserStockKeyword;
import dk.database.server.facade.DataFacadeImpl;
import org.springframework.web.bind.annotation.RequestBody;

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

    private final DataFacadeImpl data = new DataFacadeImpl();

    /**
     *
     * @param uriInfo returns url path in header
     * @return  Map</Integer,Keyword>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @GET
    public Response getAllKeywords(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, Keyword> keywords = data.getAllKeywords();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(keywords)
                .build();
    }

    /**
     *
     * @param keywordId
     * @param uriInfo returns url path in header
     * @return Keyword
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/{keywordId}")
    @GET
    public Response getKeywordById(@PathParam("keywordId") int keywordId, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Keyword keyword = data.getKeywordById(keywordId);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(keyword)
                .build();
    }

    @Path("/{userId}/stocks/{stockName}")
    @GET
    public Response getKeywordByUserIdAndStockName(
            @PathParam("userId") int userId,
            @PathParam("stockName") String stockName,
            @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {

        UserStockKeyword userStockKeyword = data.getKeywordByUserIdAndStockName(userId, stockName);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(userStockKeyword)
                .build();
    }

    /**
     *
     * @param keywordCreation
     * @param uriInfo returns url path in header
     * @return Keyword
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @POST
    public Response addKeyword(@RequestBody KeywordCreation keywordCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Keyword keyword = data.addKeyword(keywordCreation);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(keyword)
                .build();
    }
}
