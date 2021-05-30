package dk.database.server.service;

import dk.database.server.config.DBConnection;
import dk.database.server.domain.StockCreation;
import dk.database.server.entities.Stock;
import dk.database.server.service.interfaces.StockService;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StockServiceImpl implements StockService {

    private final DBConnection db = new DBConnection();

    /**
     *
     * @return Map</Integer,Stock>
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     *
     * @param stockId
     * @return Stock
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

    /**
     *
     * @param stockCreation
     * @return Stock
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Override
    public Stock addStock(StockCreation stockCreation) throws SQLException, ClassNotFoundException {
        try(Connection connection = db.connect())
        {
            String sql = "{ ? = call add_stock(?)}";

            Stock stock;

            try (CallableStatement stmt= connection.prepareCall(sql))
            {
                stmt.setString(2,stockCreation.getStockName());
                stmt.registerOutParameter(1,Types.INTEGER);
                stmt.execute();

                int newId = stmt.getInt(1);
                stock = new Stock(newId, stockCreation.getStockName());
                return stock;
            }
        }
    }
}
