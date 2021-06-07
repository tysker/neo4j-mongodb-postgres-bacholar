package dk.database.server.controller;

import dk.database.server.domain.StockCreation;
import dk.database.server.entities.Stock;
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

@Path("/stocks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StockController {

    private final DataFacadeImpl data = new DataFacadeImpl();

    /**
     *
     * @param uriInfo returns url path in header
     * @return Map<Integer,Stock>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @GET
    public Response getAllStocks(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, Stock> stocks = data.getAllStocks();

        //if(stocks == null)
        //{
        //    throw new DataNotFoundException("Sorry, we were not able to handle your request.");
        //}

        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(stocks)
                .build();
    }

    /**
     *
     * @param stockId
     * @param uriInfo returns url path in header
     * @return Stock
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/{stockId}")
    @GET
    public Response getStockById(
            @PathParam("stockId") int stockId, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Stock stock = data.getStockById(stockId);

        //if(stock == null)
        //{
        //    throw new DataNotFoundException("Sorry, we were not able to handle your request. Stock with id " + stockId + " can't be found in our system. ");
        //}

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(stock)
                .build();
    }

    /**
     *
     * @param stockCreation
     * @param uriInfo returns url path in header
     * @return Stock
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Path("/")
    @POST
    public Response addStock(@RequestBody StockCreation stockCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Stock _stock = data.addStock(stockCreation);

        //if(_stock == null )
        //{
        //    throw new DataConflictException("Sorry, we were not able to handle your request. " + stockCreation.getStockName() + " can not be added.");
        //}

        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.CREATED)
                .entity(_stock)
                .build();
    }
}
