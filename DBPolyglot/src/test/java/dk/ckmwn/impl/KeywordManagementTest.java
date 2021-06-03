package dk.ckmwn.impl;

import dk.ckmwn.TestBase;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KeywordManagementTest extends TestBase {

    private Keyword keyword = new Keyword("Elon Musk");

    @BeforeAll
    public void setupKm() {
//        MongoDatabase db = mongoClient.getDatabase("abc");
//        db.createCollection("articles");
        km = new KeywordManagementImpl(neoDriver);
        sm = new StockManagementImpl(neoDriver);
    }

    @BeforeEach
    public void flushDb() {
        km.deleteKeyword(keyword);
    }

    @Test
    public void mustCreateKeyword() {
        //Arrange

        //Act
        boolean res = km.createKeyword(keyword);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustNotCreateKeywordWithNullText() {
        //Arrange
        Keyword keyword = new Keyword(null);
        //Act
        boolean res = km.createKeyword(keyword);
        //Assert
        assertFalse(res);
    }

    @Test
    public void mustDeleteKeyword() {
        //Arrange

        km.createKeyword(keyword);
        //Act
        boolean res = km.deleteKeyword(keyword);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustNotDeleteKeywordWithNullText() {
        //Arrange
        Keyword keyword = new Keyword(null);
        km.createKeyword(keyword);
        //Act
        boolean res = km.deleteKeyword(keyword);
        //Assert
        assertFalse(res);
    }

    @Test
    public void mustGetKeywordFromString() {
        //Arrange

        km.createKeyword(keyword);
        //Act
        Keyword persistedKeyword = km.getKeyword("Elon Musk");
        //Assert
        assertEquals(persistedKeyword.getText(), keyword.getText());
    }

    @Test
    public void mustAddKeywordToStock() {
        //Arrange

        Stock stock = new Stock("Tesla");
        km.createKeyword(keyword);
        sm.createStock(stock);
        //Act
        boolean res = km.addKeywordToStock(keyword, stock);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustRemoveKeywordFromStock() {
        //Arrange
        Stock stock = new Stock("Tesla");
        km.createKeyword(keyword);
        sm.createStock(stock);
        //Act
        boolean res = km.removeKeywordFromStock(keyword, stock);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustGetSuggestedKeywords() {
        //Arrange
        Stock tsla = new Stock("Tesla");
        Stock bmwi = new Stock("BMWi");
        Keyword batteries = new Keyword("Batteries");
        sm.createStock(tsla);
        sm.createStock(bmwi);
        km.createKeyword(batteries);
        km.createKeyword(keyword);
        km.addKeywordToStock(batteries, tsla);
        km.addKeywordToStock(batteries, bmwi);
        km.addKeywordToStock(keyword, tsla);
        //Act
        List<Keyword> keywordList = km.suggestKeywordsForStock(bmwi, 3);
        //Assert
        assertEquals(keyword.getText(), keywordList.get(1).getText());
    }

}
