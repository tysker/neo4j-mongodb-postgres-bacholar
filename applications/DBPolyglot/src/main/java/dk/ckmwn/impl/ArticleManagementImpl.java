package dk.ckmwn.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import dk.ckmwn.contract.ArticleManagement;
import dk.ckmwn.dto.Article;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.neo4j.driver.*;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.neo4j.driver.Values.parameters;


public class ArticleManagementImpl implements ArticleManagement, AutoCloseable {

    private MongoCollection<Document> articles;
    private Driver neoDriver;

    public ArticleManagementImpl(MongoCollection<Document> articles, Driver neoDriver) {
        this.articles = articles;
        this.neoDriver = neoDriver;
    }

    @Override
    public void close() throws Exception
    {
        neoDriver.close();
    }

    /**
     * Opretter artikel i MongoDB.
     * Article.id vil være opdateret med id fra MongoDB hvis persistering lykkes.
     *
     * @param article Article object
     * @return true hvis article persisteres ellers false.
     */
    private boolean createMongoArticle(Article article) {
        if (article.getId() == null) {
            Document doc = new Document("content", article.getContent());
            articles.insertOne(doc);
            String id = doc.get("_id").toString();
            if (id != null) {
                article.setId(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean createArticle(Article article) {
        return createMongoArticle(article) && createNeoArticle(article);
    }

    /**
     * Opretter artikel i Neo4j.
     * Article vil have samme id som i MongoDB.
     *
     * @param article Article object
     * @return true hvis article persisteres ellers false.
     */
    private boolean createNeoArticle(Article article) {
        if (article != null && article.getId() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("CREATE (a: Article) " +
                                        "SET a.id=$id, a.createdAt=$createdAt, a.summary=$summary, a.rating=$rating RETURN a;",
                                parameters("id", article.getId(),
                                        "createdAt", article.getCreatedAt().toString(),
                                        "summary", article.getSummary(),
                                        "rating", article.getRating()));


                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }

        return false;
    }

    @Override
    public boolean deleteArticle(String id) {
        return deleteMongoArticle(id) && deleteNeoArticle(id);
    }

    /**
     * Fjerner artikel i MongoDB.
     *
     *
     * @param id String
     * @return true hvis article fjernes ellers false.
     */
    private boolean deleteMongoArticle(String id) {
        if (id != null) {
            Article article = getArticle(id);
            if (article != null) {
                DeleteResult res = articles.deleteOne(eq("_id", new ObjectId(id)));
                return res.getDeletedCount() == 1;
            }
        }
        return false;
    }

    /**
     * Fjerner artikel i Neo4j.
     *
     *
     * @param id String
     * @return true hvis article fjernes ellers false.
     */
    public boolean deleteNeoArticle(String id) {
        try (Session session = neoDriver.session()) {
            return session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run("MATCH (n:Article) where n.id = $id DELETE n",
                            parameters("id", id));
                    return true;
                }
            });
        } catch (Exception e) {
        }
        return false;
    }


    /**
     * Henter artikel i MongoDB samt Neo4j og samler et objekt.
     *
     *
     * @param id String
     * @return Article hvis article findes ellers null
     */
    @Override
    public Article getArticle(String id) {
        if (id != null) {
            Document document = articles.find(eq("_id", new ObjectId(id))).first();
            if (!document.isEmpty()) {
                Article article = Article.fromDoc(document);
                try (Session session = neoDriver.session()) {
                    return session.writeTransaction(new TransactionWork<Article>() {
                        @Override
                        public Article execute(Transaction transaction) {
                            Result result = transaction.run("MATCH (n:Article) where n.id = $id return n",
                                    parameters("id", article.getId()));
                            var fields = result.single().fields().get(0);
                            article.setSummary(fields.value().asNode().get("summary").asString());
                            article.setRating(fields.value().asNode().get("rating").asDouble());

                            return article;
                        }
                    });
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * Opdaterer artikel i MongoDB samt Neo4j.
     *
     *
     * @param article Article
     * @return true hvis article opdateres ellers false
     */
    @Override
    public boolean updateArticle(Article article) {
        if (article.getId() != null) {
            Document document = articles.find(eq("_id", new ObjectId(article.getId()))).first();
            if (document != null) {
                Article persistedArticle = Article.fromDoc(document);
                if (!article.getContent().equals(persistedArticle.getContent())) {
                    UpdateResult ur = articles.updateOne(eq("_id", new ObjectId(article.getId())), combine(set("content", article.getContent())));
                    if (ur.getModifiedCount() == 0) return false;
                }
                try (Session session = neoDriver.session()) {
                    return session.writeTransaction(new TransactionWork<Boolean>() {
                        @Override
                        public Boolean execute(Transaction transaction) {
                            Result result = transaction.run("MATCH (a: Article) WHERE a.id = $id " +
                                            "SET a.summary=$summary, a.rating=$rating RETURN a;",
                                    parameters("id", article.getId(),
                                            "summary", article.getSummary(),
                                            "rating", article.getRating()));
                            return true;
                        }
                    });
                } catch (Exception e) {
                }
                return true;
            }
        }
        return false;
    }
}
