package app.persistence;

import app.entities.Order;
import app.entities.Topping;
import app.entities.User;
import app.exceptions.DatabaseException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminMapper {

    public static List<User> viewAllUsersBalance(ConnectionPool connectionPool) throws DatabaseException {
        List<User> userList = new ArrayList<>();

        String sql = "SELECT username, balance FROM public.user\n" +
                "ORDER BY username";


        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("username");
                Double price = rs.getDouble("balance");
                userList.add(new User(name, price));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        return userList;
    }

    public static void adminUpdateBalance(User user, double deposit, ConnectionPool connectionPool) throws DatabaseException {
        String selectSql = "SELECT balance FROM public.user WHERE user_id = ?";
        String updateSql = "UPDATE public.user SET balance = ? WHERE user_id = ?";

        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement selectPs = connection.prepareStatement(selectSql);
                PreparedStatement updatePs = connection.prepareStatement(updateSql)
        ) {
            // Fetch the current balance from the database
            selectPs.setInt(1, user.getUserId());
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                double newBalance = currentBalance + deposit;

                // Update the balance in the database
                updatePs.setDouble(1, newBalance);
                updatePs.setInt(2, user.getUserId());
                updatePs.executeUpdate();

                // Update the user object with the new balance
                user.setBalance(newBalance);
            } else {
                throw new DatabaseException("User not found");
            }
        } catch (SQLException e) {
            throw new DatabaseException("Error updating balance", e.getMessage());
        }
    }

    public static List<Order> viewAllOrders(ConnectionPool connectionPool) throws DatabaseException {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM public.order";
        try (
                Connection connection = connectionPool.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int order_number = rs.getInt("order_number");
                int user_number = rs.getInt("user_number");
                double order_price = rs.getDouble("order_price");
                LocalDate order_date = rs.getDate("order_date").toLocalDate();
                Timestamp order_pickuptimeTimestamp = rs.getTimestamp("order_pickuptime");
                LocalDateTime order_pickuptime = order_pickuptimeTimestamp.toLocalDateTime();
                orderList.add(new Order(order_number, user_number, order_price, order_date, order_pickuptime));
            }
        } catch (SQLException e) {
            throw new DatabaseException("Fejl", e.getMessage());
        }
        // Print each order information underneath each other
        for (Order order : orderList) {
            System.out.println(order);
            System.out.println(); // Add a newline after each order
        }

        return orderList;
    }
}
