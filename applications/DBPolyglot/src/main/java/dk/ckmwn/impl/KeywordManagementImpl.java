package dk.ckmwn.impl;

import dk.ckmwn.contract.KeywordManagement;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import org.neo4j.driver.*;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class KeywordManagementImpl implements KeywordManagement {

    private Driver neoDriver;

    public KeywordManagementImpl(Driver neoDriver) {
        this.neoDriver = neoDriver;
    }

    public boolean dropGraph() {
        try (Session session = neoDriver.session()) {
            return session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run("CALL gds.graph.drop('myGraph');");
                    return true;
                }
            });
        } catch (Exception e) {
        }
        return false;
    }

    private boolean setupGraph() {
        try (Session session = neoDriver.session()) {
            return session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction transaction) {
                    Result result = transaction.run("CALL gds.graph.create('myGraph', '*', {Part: {orientation:'UNDIRECTED'}});");
                    return true;
                }
            });
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean createKeyword(Keyword keyword) {
        if (keyword != null && keyword.getText() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("CREATE (k:Keyword) " +
                                        "SET k.text=$text RETURN k;",
                                parameters("text", keyword.getText()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public boolean deleteKeyword(Keyword keyword) {
        if (keyword != null && keyword.getText() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (k:Keyword) WHERE k.text = $text DELETE k;",
                                parameters("text", keyword.getText()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public Keyword getKeyword(String text) {
        if (text != null) {
            try (Session session = neoDriver.session()) {
                return new Keyword(session.writeTransaction(new TransactionWork<String>() {
                    @Override
                    public String execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (k:Keyword {text: $text}) RETURN k.text;",
                                parameters("text", text));
                        return result.single().get(0).asString();

                    }
                }));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean addKeywordToStock(Keyword keyword, Stock stock) {
        if (keyword != null && keyword.getText() != null && stock != null && stock.getSymbol() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {

                        Result result = transaction.run("MATCH (s:Stock {symbol: $symbol}), (k:Keyword {text: $text}) " +
                                        "CREATE (s)-[r:Part]->(k);",
                                parameters("text", keyword.getText(), "symbol", stock.getSymbol()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public boolean removeKeywordFromStock(Keyword keyword, Stock stock) {
        if (keyword != null && keyword.getText() != null && stock != null && stock.getSymbol() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (s:Stock {symbol: $symbol})-[r:Part]->(k:Keyword {text: $text})\n" +
                                        "DELETE r;",
                                parameters("text", keyword.getText(), "symbol", stock.getSymbol()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public List<Keyword> suggestKeywordsForStock(Stock stock, int width) {
        dropGraph();
        setupGraph();
        try (Session session = neoDriver.session()) {
            return session.writeTransaction(new TransactionWork<List<Keyword>>() {
                @Override
                public List<Keyword> execute(Transaction transaction) {
                    Result result = transaction.run("MATCH (s:Stock{symbol:$symbol}) " +
                                    "WITH id(s) AS startNode " +
                                    "CALL gds.alpha.bfs.stream('myGraph', {startNode: startNode, maxDepth:$width}) " +
                                    "YIELD path " +
                                    "UNWIND [ n in nodes(path) | n.text ] AS texts " +
                                    "RETURN texts " +
                                    "ORDER BY texts",
                            parameters("width", width, "symbol", stock.getSymbol()));
                    List<Keyword> keywords = new ArrayList<>();
                    while (result.hasNext()) {
                        String text = result.peek().get(0).asString();
                        if(!text.equalsIgnoreCase("null")) {
                            keywords.add(new Keyword(text));
                        }
                        result.next();
                    }
                    return keywords;
                }
            });
        } catch (Exception e) {
        }
        return null;
    }
}
