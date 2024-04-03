package app;

import app.config.ThymeleafConfig;
import app.entities.Cupcake;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.CupcakeMapper;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.sql.Connection;


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

        User user = new User(1, 2, "s@gmail.com", "sBeck", "1234", "user", 230);

        int toppingNumber = 1;
        String toppingName = "Chocolate";
        int bottomNumber = 2;
        String bottomName = "Vanilla";
        int quantity = 3;
        double orderlinePrice = 30;

        try {
            // Adding cupcake
            Cupcake cupcake = CupcakeMapper.addCupcake(user, toppingNumber, toppingName, bottomNumber, bottomName, quantity, orderlinePrice, connectionPool);

            // Checking if cupcake was added successfully
            if (cupcake != null) {
                System.out.println("Cupcake added successfully: " + cupcake.toString());
            } else {
                System.out.println("Failed to add cupcake.");
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
            // Handle database exception
        }

    }
}