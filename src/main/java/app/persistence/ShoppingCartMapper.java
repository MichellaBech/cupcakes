package app.persistence;

import app.entities.Orderline;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartMapper {

    public static List<Orderline> getAllOrderlines(User user, ConnectionPool connectionPool) throws DatabaseException {
        List<Orderline> orderList = new ArrayList<>();
        user = new User();

        int getuser_number = user.getUserNumber();

        String sql = "SELECT topping_name, bottom_name, quantity, orderline_price  FROM public.orderline\n" +
                " WHERE user_number = getuser_number ORDER BY user_number ASC";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String topping_name = rs.getString("topping_name");
                String bottom_name = rs.getString("bottom_name");
                double price = rs.getDouble("orderline_price");
                orderList.add(new Orderline(topping_name, bottom_name, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return orderList;
    }

    public static double showOrderPrice(User user, ConnectionPool connectionPool) throws DatabaseException {
        user = new User(user.getUserId(), user.getUserNumber(), user.getUserMail(), user.getUsername(), user.getPassword(), user.getRole(), user.getBalance());

        int getuser_number = user.getUserNumber();
        String sql = " SELECT order_price FROM public.order\n" +
                "WHERE user_number = ?";

        double price = 0;
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)

        ) {
            // Set the user number as a parameter
            ps.setInt(1, user.getUserNumber());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Get the price from the result set
                price = rs.getDouble("order_price");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return price;
    }

    public static double payment(User user, ConnectionPool connectionPool) throws DatabaseException {

        user = new User(user.getUserId(), user.getUserNumber(), user.getUserMail(), user.getUsername(), user.getPassword(), user.getRole(), user.getBalance());

        double orderPrice = showOrderPrice(user, connectionPool);
        double newBalance = user.getBalance() - orderPrice;

        UserMapper.updateBalance(user, newBalance, connectionPool);

        return newBalance;
    }

}
