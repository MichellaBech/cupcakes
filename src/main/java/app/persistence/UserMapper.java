
package app.persistence;

import app.exceptions.DatabaseException;
import app.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class UserMapper
    {
        public static User login(String username, String password, ConnectionPool connectionPool) throws DatabaseException
        {
            {
                String sql = "select * from public.\"user\" where username=? and password=?";

                try (
                        Connection connection = connectionPool.getConnection();
                        PreparedStatement ps = connection.prepareStatement(sql)
                )
                {
                    ps.setString(1, username);
                    ps.setString(2, password);

                    ResultSet rs = ps.executeQuery();
                    if (rs.next())
                    {
                        int userId = rs.getInt("user_id");
                        int userNumber = rs.getInt("user_number");
                        String userMail = rs.getString("user_email");
                        String role = rs.getString("role");
                        double balance = rs.getDouble("balance");
                        return new User(userId, userNumber, userMail, username, password, role, balance);
                    } else
                    {
                        throw new DatabaseException("Fejl i login. Prøv igen");
                    }
                }
                catch (SQLException e)
                {
                    throw new DatabaseException("DB fejl", e.getMessage());
                }
            }
        }

        public static void createuser(String email, String username, String password, ConnectionPool connectionPool) throws DatabaseException
        {
            String sql = "insert into public.user (user_email, username, password, balance, role) values (?,?,?,?,?)";

            try (
                    Connection connection = connectionPool.getConnection();
                    PreparedStatement ps = connection.prepareStatement(sql)
            )
            {
                ps.setString(1, email);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setDouble(4, 400);
                ps.setString(5, "user");

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected != 1)
                {
                    throw new DatabaseException("Fejl ved oprettelse af ny bruger");
                }
            }
            catch (SQLException e)
            {
                String msg = "Der er sket en fejl. Prøv igen";
                if (e.getMessage().startsWith("ERROR: duplicate key value"))
                {
                    msg = "Brugernavnet eller email findes allerede. Vælg et andet eller login";
                }
                throw new DatabaseException(msg, e.getMessage());
            }
        }
    }
