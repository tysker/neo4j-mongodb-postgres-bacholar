package dk.database.server.service;

import dk.database.server.config.DBConnection;
import dk.database.server.entities.Keyword;
import dk.database.server.entities.Stock;
import dk.database.server.service.interfaces.StockService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class StockServiceImpl implements StockService {

    private final DBConnection db = new DBConnection();

    @Override
    public Map<Integer, Stock> getAllStocks() throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {
            Map<Integer, Stock> stocks = new HashMap<>();

            String sql = "SELECT * FROM stocks;";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ResultSet rs = ps.executeQuery();

                while (rs.next())
                {
                    int id = rs.getInt("id");
                    String stockName = rs.getString("stockname");
                    stocks.put(id, new Stock(id, stockName));

                }
                return stocks;
            }
        }
    }

    @Override
    public Stock getStockById(int stockId) throws SQLException, ClassNotFoundException {

        try(Connection connection = db.connect())
        {
            Stock stock = new Stock();

            String sql = "SELECT * FROM stocks WHERE id = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setInt(1, stockId);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int id = rs.getInt("id");
                    String st = rs.getString("stockname");
                    stock = new Stock(id, st);;
                }
                return stock;
            }
        }
    }

    @Override
    public Stock addStock(Stock stock) throws SQLException, ClassNotFoundException {
        return null;
    }
}
