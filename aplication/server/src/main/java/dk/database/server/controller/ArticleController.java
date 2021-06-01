package dk.database.server.controller;

import dk.ckmwn.dto.Article;
import dk.database.server.domain.UserCreation;
import dk.database.server.domain.UserKeywordCreation;
import dk.database.server.domain.UserStockCreation;
import dk.database.server.entities.User;
import dk.database.server.entities.UserKeyword;
import dk.database.server.exceptions.dataconflict.DataConflictException;
import dk.database.server.exceptions.datanotfound.DataNotFoundException;
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

@Path("/article")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArticleController {

    private final DataFacadeImpl data = new DataFacadeImpl();

    @Path("/")
    @POST
    public Response createArticle(@RequestBody Article article, @Context UriInfo uriInfo) {
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        boolean created = data.createArticle(article);

        if(!created)
        {
            throw new DataConflictException("Sorry, we were not able to handle your request.");
        }

        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(article)
                .build();
    }

}
