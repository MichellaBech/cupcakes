package app.controllers;

import app.entities.User;
import app.exceptions.DatabaseException;
import app.persistence.ConnectionPool;
import app.persistence.UserMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static app.persistence.UserMapper.login;

public class UserController {

    public static void addRoutes(Javalin app, ConnectionPool connectionPool)
    {
        app.post("/login", ctx -> login(ctx, connectionPool));
        //app.get("/logout", ctx -> logout(ctx));

    }

    private static void logout(Context ctx) {

    }

    public static void login(Context ctx, ConnectionPool connectionPool)  {

        //Get form parametre
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        try {
            User user = UserMapper.login(username, password, connectionPool); // Call UserMapper.login
        } catch (DatabaseException e) {
            ctx.render("index.html");
        }

    }
}
