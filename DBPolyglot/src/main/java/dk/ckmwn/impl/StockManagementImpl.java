package dk.ckmwn.impl;

import dk.ckmwn.contract.StockManagement;
import dk.ckmwn.dto.Keyword;
import dk.ckmwn.dto.Stock;
import org.neo4j.driver.*;
import static org.neo4j.driver.Values.parameters;

public class StockManagementImpl implements StockManagement {

    private Driver neoDriver;

    public StockManagementImpl(Driver neoDriver) {
        this.neoDriver = neoDriver;
    }

    @Override
    public boolean createStock(Stock stock) {
        if (stock != null && stock.getSymbol() != null && !stock.getSymbol().equals("")) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("CREATE (s: Stock) " +
                                        "SET s.symbol=$symbol RETURN s;",
                                parameters("symbol", stock.getSymbol()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public boolean deleteStock(Stock stock) {
        if (stock != null && stock.getSymbol() != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (s: Stock) WHERE s.symbol = $symbol DELETE s;",
                                parameters("symbol", stock.getSymbol()));
                        return true;
                    }
                });
            } catch (Exception e) {
            }
        }
        return false;
    }

    @Override
    public Stock getStock(String symbol) {
        if (symbol != null && !symbol.equals("")) {
            try (Session session = neoDriver.session()) {
                return new Stock(session.writeTransaction(new TransactionWork<String>() {
                    @Override
                    public String execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (s:Stock {symbol: $symbol}) RETURN s.symbol;",
                                parameters("symbol", symbol));
                        return result.single().get(0).asString();
                    }
                }));
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    public boolean updateStock(String fromSymbol, String toSymbol) {
        if (fromSymbol != null && toSymbol != null) {
            try (Session session = neoDriver.session()) {
                return session.writeTransaction(new TransactionWork<Boolean>() {
                    @Override
                    public Boolean execute(Transaction transaction) {
                        Result result = transaction.run("MATCH (s: Stock) WHERE s.symbol = $fromSymbol " +
                                        "SET s.symbol=$toSymbol RETURN s;",
                                parameters("fromSymbol", fromSymbol, "toSymbol", toSymbol));
                        return true;
                    }
                });
            } catch (Exception e) {
            }

        }
        return false;
    }
}

