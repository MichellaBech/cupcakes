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

        /*Insertion Order: Unlike HashMap, which does not guarantee any specific order of its elements,
        LinkedHashMap maintains the order in which the entries were inserted into the map.
        When iterating over a LinkedHashMap, the elements are returned in the same order in which they were inserted.
         */

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

        public static Cupcake addCupcake(User user, int topping_number, String topping_name, int bottom_number, String bottom_name,
                                     int quantity, double orderline_price, ConnectionPool connectionPool) throws DatabaseException {
        Cupcake cupcake = null;

        String sql = "insert into orderline(user_number, topping_number, topping_name," +
                " bottom_number, bottom_name, quantity, orderline_price, orderline_date) values (?,?,?,?,?,?,?, current_timestamp)";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, user.getUserId());
            ps.setInt(2, topping_number);
            ps.setString(3, topping_name);
            ps.setInt(4, bottom_number);
            ps.setString(5, bottom_name);
            ps.setInt(6, quantity);
            ps.setDouble(7, orderline_price);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                int newOrderline_number = rs.getInt(1);
                cupcake = new Cupcake(user.getUserId(), topping_number, topping_name, bottom_number, bottom_name,
                        quantity, orderline_price);
            } else
            {
                throw new DatabaseException("Fejl under indsætning af task: " + topping_name + " " + bottom_name);
            }
        }
        catch (SQLException e)
        {
            throw new DatabaseException("Fejl i DB connection", e.getMessage());
        }
        return cupcake;
    }




}
