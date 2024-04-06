package app;

import app.config.ThymeleafConfig;
import app.controllers.CupcakeController;
import app.controllers.UserController;
import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.AdminMapper;
import app.persistence.ConnectionPool;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.List;

import static app.persistence.ShoppingCartMapper.*;


public class Main {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "cupcake";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
    public static void main(String[] args) throws DatabaseException {
        // Initializing Javalin and Jetty webserver

       Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
            config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
        }).start(7070);

        // Routing

        app.get("/", ctx ->  ctx.render("index.html"));
        UserController.addRoutes(app, connectionPool);
        app.post("/", ctx ->  ctx.render("index.html"));
        CupcakeController.addRoutes(app, connectionPool);


    }
}