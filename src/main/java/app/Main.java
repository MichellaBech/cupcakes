package app;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.AdminMapper;
import app.persistence.ConnectionPool;

import static app.persistence.ShoppingCartMapper.payment;
import static app.persistence.ShoppingCartMapper.showOrderPrice;


public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
    public static void main(String[] args) throws DatabaseException {
        // Initializing Javalin and Jetty webserver

     /*   Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html")); */
        User user = new User(1, 1, "beck@gmail.com", "beck", "1234", "user", 250);


        AdminMapper.viewAllOrders(connectionPool);
    }
}