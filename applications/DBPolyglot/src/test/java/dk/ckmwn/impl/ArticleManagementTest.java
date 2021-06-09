package dk.ckmwn.impl;
import com.mongodb.client.MongoDatabase;
import dk.ckmwn.TestBase;
import dk.ckmwn.dto.Article;
import dk.ckmwn.dto.Keyword;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleManagementTest extends TestBase {

    @BeforeAll
    public void setupAm() {
        MongoDatabase db = mongoClient.getDatabase("abc");
        db.createCollection("articles");
        am = new ArticleManagementImpl(db.getCollection("articles"), neoDriver);
    }

    @Test
    public void mustCreateArticle() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()), new Keyword("hej"));
        //Act
        boolean res = am.createArticle(article);
        //Assert
        assertTrue(res);
        assertNotNull(article.getId());
    }

    @Test
    public void mustNotCreateArticleWithId() {
        //Arrange
        Article article = new Article("kfokdovk","Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
        //Act
        boolean res = am.createArticle(article);
        //Assert
        assertFalse(res);
    }

    @Test
    public void mustDeleteArticleWithExistingId() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
        am.createArticle(article);
        String id = article.getId();
        //Act
        boolean res = am.deleteArticle(id);
        //Assert
        assertTrue(res);
    }

    @Test
    public void mustGetArticleWithValidId() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
        am.createArticle(article);
        String id = article.getId();
        //Act
        Article res = am.getArticle(id);
        //Assert
        assertEquals(res.getId(), id);
        assertEquals(res.getContent(), article.getContent());
    }

    @Test
    public void mustUpdateArticleWithValidId() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
        am.createArticle(article);
        article.setContent("Bla bla bla");
        article.setRating(0.2);
        //Act
        boolean res = am.updateArticle(article);
        Article persistedArticle = am.getArticle(article.getId());
        //Assert
        assertTrue(res);
        assertEquals(persistedArticle.getContent(), article.getContent());
        assertEquals(persistedArticle.getRating(), article.getRating());
    }

    @Test
    public void mustNotUpdateArticleWithInvalidId() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
//        The 12-byte ObjectId value consists of:
//        a 4-byte timestamp value, representing the ObjectId's creation, measured in seconds since the Unix epoch
//        a 5-byte random value
//        a 3-byte incrementing counter, initialized to a random value
        article.setId("507f1f77bcf86cd799439011");
        //Act
        boolean res = am.updateArticle(article);
        //Assert
        assertFalse(res);
    }

    @Test
    public void mustNotUpdateArticleWithoutId() {
        //Arrange
        Article article = new Article("Bla bla", "Bla", 0.87, new Date(System.currentTimeMillis()),new Keyword("hej"));
        //Act
        boolean res = am.updateArticle(article);
        //Assert
        assertFalse(res);
    }


}
