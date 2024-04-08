package app.persistence;

import app.entities.Bottom;
import app.entities.Cupcake;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CupcakeMapper {
    public static Map<String, Double> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {



        Map<String, Double> toppingMap = new LinkedHashMap<>();

        String sql = "SELECT topping_name, topping_price FROM public.topping\n" +
                "ORDER BY topping_id ASC";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("topping_name");
                Double price = rs.getDouble("topping_price");
                toppingMap.put(name, price);

            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return toppingMap;
    }

    public static Map<String, Double> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        Map<String, Double> bottomMap = new LinkedHashMap<>();

        String sql = "SELECT bottom_name, bottom_price FROM public.bottom\n" +
                "ORDER BY bottom_id ASC";


        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("bottom_name");
                Double price = rs.getDouble("bottom_price");
                bottomMap.put(name, price);
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return bottomMap;
    }

    public static double getBottomPrice(String bottom_name, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT bottom_price FROM public.bottom WHERE bottom_name = ?";

        Double price = null;
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setString(1, bottom_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble("bottom_price");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return price;
    }

    public static double getToppingPrice(String topping_name, ConnectionPool connectionPool) throws DatabaseException {
        String sql = "SELECT topping_price FROM public.topping WHERE topping_name = ?";

        Double price = null;
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
                ) {
            // Set the value of the parameter before executing the query
            ps.setString(1, topping_name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble("topping_price");
            }
        } catch (SQLException e) {
            throw  new DatabaseException("Fejl", e.getMessage());
        }
        return price;
    }
}
