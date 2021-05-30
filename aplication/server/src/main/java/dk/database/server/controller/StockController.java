package dk.database.server.controller;

import dk.database.server.domain.StockCreation;
import dk.database.server.domain.UserCreation;
import dk.database.server.entities.Stock;
import dk.database.server.entities.User;
import dk.database.server.facade.DataFacadeImpl;
import dk.database.server.service.StockServiceImpl;
import dk.database.server.service.interfaces.StockService;
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

    @Path("/")
    @GET
    public Response getAllStocks(@Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Map<Integer, Stock> stocks = data.getAllStocks();
        URI uri = uriInfo.getAbsolutePathBuilder().build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(stocks)
                .build();
    }

    @Path("/{stockId}")
    @GET
    public Response getStockById(@PathParam("stockId") int stockId, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Stock stock = data.getStockById(stockId);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .status(Response.Status.OK)
                .entity(stock)
                .build();
    }

    @Path("/")
    @POST
    public Response addStock(@RequestBody StockCreation stockCreation, @Context UriInfo uriInfo) throws SQLException, ClassNotFoundException {
        Stock _stock = data.addStock(stockCreation);
        URI uri = uriInfo.getAbsolutePathBuilder()
                .build();
        return Response
                .created(uri)
                .status(Response.Status.OK)
                .entity(_stock)
                .build();
    }
}
