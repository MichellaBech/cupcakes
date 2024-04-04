package app.persistence;

import app.entities.Bottom;
import app.entities.Cupcake;
import app.entities.Topping;
import app.entities.User;
import app.exceptions.DatabaseException;
import org.eclipse.jetty.server.Authentication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CupcakeMapper {
    public static List<Topping> getAllToppings(ConnectionPool connectionPool) throws DatabaseException {
        List<Topping> toppingList = new ArrayList<>();

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
                toppingList.add(new Topping(name, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return toppingList;
    }

    public static List<Bottom> getAllBottoms(ConnectionPool connectionPool) throws DatabaseException {
        List<Bottom> bottomList = new ArrayList<>();

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
                bottomList.add(new Bottom(name, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return bottomList;
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


    public static Cupcake addChocolateFantasy(User user, ConnectionPool connectionPool) throws DatabaseException {
        return addCupcake(user, 1, "Chocolate", 1, "Chocolate", 1, 10.00, connectionPool);
    }

    public static Cupcake addStrawberryFairytale(User user, ConnectionPool connectionPool) throws DatabaseException {
        return addCupcake(user, 5, "Strawberry", 2, "Vanilla", 1, 11.00, connectionPool);
    }

    public static Cupcake addBlueberryBonanza(User user, ConnectionPool connectionPool) throws DatabaseException {
        return addCupcake(user, 2,"Blueberry",5, "Almond", 1, 12.00, connectionPool);
    }



}
