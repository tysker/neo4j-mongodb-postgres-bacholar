package dk.database.server.controller;

import dk.database.server.domain.KeywordCreation;
import dk.database.server.entities.Keyword;
<<<<<<< HEAD
=======
import dk.database.server.exceptions.dataconflict.DataConflictException;
import dk.database.server.exceptions.datanotfound.DataNotFoundException;
>>>>>>> server
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

        if(keywords == null)
        {
            throw new DataNotFoundException("Sorry, we were not able to handle your request.");
        }

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

        if(keyword == null)
        {
            throw new DataNotFoundException("Sorry, we were not able to handle your request. Keyword with id " + keywordId + " can't be found in our system. ");
        }

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(keyword)
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

        if(keyword == null )
        {
            throw new DataConflictException("Sorry, we were not able to handle your request. " + keywordCreation.getKeyword() + " can not be added.");
        }

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(keyword)
                .build();
    }
}
