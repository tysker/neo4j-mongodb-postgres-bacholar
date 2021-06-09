package dk.ckmwn.impl;

import dk.ckmwn.TestBase;
import dk.ckmwn.dto.Article;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StockManagementTest extends TestBase {

    @BeforeAll
    public void setupSm() {
//        MongoDatabase db = mongoClient.getDatabase("abc");
//        db.createCollection("articles");
        sm = new StockManagementImpl(neoDriver);
    }

    @Test
    public void mustCreateStock() {
        //Arrange
        Stock stock = new Stock("Tesla");
        //Act
        boolean res = sm.createStock(stock);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustNotCreateStockWithNullSymbol() {
        //Arrange
        Stock stock = new Stock(null);
        //Act
        boolean res = sm.createStock(stock);
        //Assert
        assertFalse(res);
    }

    @Test
    public void mustDeleteStock() {
        //Arrange
        Stock stock = new Stock("Tesla");
        sm.createStock(stock);
        //Act
        boolean res = sm.deleteStock(stock);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustGetStockFromString() {
        //Arrange
        Stock stock = new Stock("Tesla");
        sm.createStock(stock);
        //Act
        Stock persistedStock = sm.getStock("Tesla");
        //Assert
        assertEquals(persistedStock.getSymbol(), stock.getSymbol());
    }

    @Test
    public void mustGetNullIfStringIsEmptyOrNull() {
        //Arrange
        Stock emptyStock = new Stock("");
        Stock nullStock = new Stock(null);

        //Act
        boolean emptyRes = sm.createStock(emptyStock);
        boolean nullRes = sm.createStock(nullStock);
        //Assert
        assertFalse(emptyRes);
        assertFalse(nullRes);
    }

    @Test
    public void mustUpdateStock() {
        //Arrange
        Stock stock = new Stock("Tesla");
        sm.createStock(stock);
        String stockUpdate = "SpaceX";
        //Act
        boolean res = sm.updateStock("Tesla", stockUpdate);
        Stock updatedStock = sm.getStock(stockUpdate);
        //Assert
        assertTrue(res);
        assertEquals(stockUpdate, updatedStock.getSymbol());
    }

}
