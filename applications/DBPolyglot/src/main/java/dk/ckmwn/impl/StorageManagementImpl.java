package dk.ckmwn.impl;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import dk.ckmwn.contract.ArticleManagement;
import dk.ckmwn.contract.KeywordManagement;
import dk.ckmwn.contract.StockManagement;
import dk.ckmwn.contract.StorageManagement;
import dk.ckmwn.dto.Article;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import org.bson.Document;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import java.util.List;

public class StorageManagementImpl implements StorageManagement {

    private ArticleManagement am;
    private KeywordManagement km;
    private StockManagement sm;

    public StorageManagementImpl() {
        //Bør måske fjernes herfra? Dependency Injection
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Driver neoDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234"));
        MongoCollection<Document> articles = mongoClient.getDatabase("abc").getCollection("articles");
        this.am = new ArticleManagementImpl(articles, neoDriver);
        this.km = new KeywordManagementImpl(neoDriver);
        this.sm = new StockManagementImpl(neoDriver);
    }

    //Færdig
    @Override
    public boolean createKeyword(Keyword keyword) { return km.createKeyword(keyword); }

    //Færdig
    @Override
    public boolean createStock(Stock stock) {
        return sm.createStock(stock);
    }

    //Færsig
    @Override
    public boolean addKeywordToStock(Stock stock, Keyword keyword) {
        return km.addKeywordToStock(keyword, stock);
    }

    //Mangler i system
    @Override
    public boolean removeKeywordFromStock(Stock stock, Keyword keyword) {
        return km.removeKeywordFromStock(keyword, stock);
    }
    //Færdig
    @Override
    public List<Keyword> suggestKeywordsForStock(Stock stock, int width) {
        return km.suggestKeywordsForStock(stock, width);
    }

    //Færdig
    @Override
    public boolean createArticle(Article article) {
        return am.createArticle(article);
    }

    //Færdig
    @Override
    public Article getArticle(String id) {
        return am.getArticle(id);
    }
}
